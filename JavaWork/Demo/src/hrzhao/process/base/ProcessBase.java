package hrzhao.process.base;

import java.util.Date;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.ProcessBeanDao;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.DebugHelper;


public abstract class ProcessBase implements ProcessInterface {
	
	public ProcessBase() {
		
	}
	private String returnHomePage(String fromUserName){
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		customerBean.setProcessId(ConfigHelper.homePcsId);
		customerBean.setLasttime(new Date());
		customerBean.setProcessing(false);
		customerDao.saveOrUpdateCustomer(customerBean);
		String tips = getNextTips(customerBean);
		return tips==null? "":tips;
	}
	
	
	@Override
	public final String doProcess(ReqMessageBean msgBean) {
		String msg = "";
		if(ConfigHelper.returnSignal.equals(msgBean.getContent())){
			return returnHomePage(msgBean.getFromUserName());
		}
		getProcessData();//应该放在接口里
		if(pcsBean == null){
			//出错
			DebugHelper.log("ProcessBase","getProcessData("+processId+") is null");
			return null;
		}
		
		//业务逻辑实际要处理的内容
		ProcessResult pcsResult = doProcessExt(msgBean);
		Integer nextProcessId;
		if(pcsResult != null){
			if(pcsResult.getResult()){
				nextProcessId = pcsBean.getNextId();
			}else{
				nextProcessId = pcsBean.getNegativeId();
			}
			msg = pcsResult.getMsg()+"\n";
		}else{
			DebugHelper.log("ProcessBase","doProcessExt() is null");
			return "系统错误，请联系管理员";
		}
		CustomerBean customerBean = updateNextProcessId(msgBean.getFromUserName(),nextProcessId);
		String nextTips = getNextTips(customerBean);
		
		if(nextTips == null){
			msg += "选项有误\n";
			nextTips = returnHomePage(customerBean.getName());
		}
		msg += nextTips;
		
		return msg;
	}
	
	public JSONObject getParam(){
		JSONObject jsonObj = null;
		try{
			getProcessData();
			if(this.pcsBean != null)
			{
				jsonObj = JSONObject.fromObject(pcsBean.getParam());
			}
		}catch(Exception e){
			DebugHelper.log("ProcessBase","getParam() error\n " + e.toString());
		}
		return jsonObj;
	}

	
	@Override 
	public String getTips(CustomerBean customerBean){
		//默认的就不读取用户信息了
		String tips = "";
		getProcessData();
		if(pcsBean != null && pcsBean.getUseTips()){
			tips = pcsBean.getTips();
		}
		return tips;
	}
	//updateNextProcessId()以后customerBean.getProcessId()已是nextProcessId
	private String getNextTips(CustomerBean customerBean){
		ProcessInterface pcsInterface = ProcessFactory.createProcess(customerBean.getProcessId());
		if(pcsInterface == null){
			return null;
		}
		return ProcessFactory.createProcess(customerBean.getProcessId()).getTips(customerBean);
	}
	
	protected abstract ProcessResult doProcessExt(ReqMessageBean msgBean);

	private Integer processId;
	
	
	public Integer getProcessId() {
		return processId;
	}
	
	protected CustomerBean updateNextProcessId(String fromUserName,Integer processId){	
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		customerBean.setProcessId(processId);
		customerBean.setLasttime(new Date());
		customerBean.setProcessing(false);
		customerDao.saveOrUpdateCustomer(customerBean);
		return customerBean;
	}
	@Override
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	
	private ProcessBean pcsBean = null;

	@Override
	public ProcessBean getProcessData(){
		if(pcsBean == null){
			//在实例中只做一次
			ProcessBeanDao pcsDao = new ProcessBeanDao();
			pcsBean = pcsDao.getProcessBean(processId);
		}
		return pcsBean;
	}
}
