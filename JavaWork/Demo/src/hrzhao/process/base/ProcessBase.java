package hrzhao.process.base;

import java.util.Date;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.ProcessBeanDao;
import hrzhao.utils.DebugHelper;


public abstract class ProcessBase implements ProcessInterface {
	
	public ProcessBase() {
	}
	
	
	@Override
	public final String doProcess(ReqMessageBean msgBean) {
		String msg = null;
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
		nextProcessId = updateNextProcessId(msgBean.getFromUserName(),nextProcessId);
		String tips = getNextTips(nextProcessId);
		if(tips != null){
			msg += tips;
		}
		return msg;
	}
	
	public JSONObject getParam(){
		JSONObject jsonObj = null;
		try{
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
	public String getTips(){
		String tips = null;
		getProcessData();
		if(pcsBean != null && pcsBean.getUseTips()){
			tips = pcsBean.getTips();
		}
		return tips;
	}
	
	private String getNextTips(Integer nextProcessId){
		String tips = ProcessFactory.createProcess(nextProcessId).getTips();
		return tips;
	}
	
	protected abstract ProcessResult doProcessExt(ReqMessageBean msgBean);

	private Integer processId;
	
	
	public Integer getProcessId() {
		return processId;
	}
	
	protected Integer updateNextProcessId(String fromUserName,Integer processId){	
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		customerBean.setProcessId(processId);
		customerBean.setLasttime(new Date());
		customerBean.setProcessing(false);
		customerDao.saveOrUpdateCustomer(customerBean);
		return processId;
	}
	@Override
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	
	private ProcessBean pcsBean = null;

	@Override
	public ProcessBean getProcessData(){
		if(pcsBean == null){
			ProcessBeanDao pcsDao = new ProcessBeanDao();
			pcsBean = pcsDao.getProcessBean(processId);
		}
		return pcsBean;
	}
}
