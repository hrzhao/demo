package hrzhao.process.base;

import java.util.Date;

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
		ProcessBean pcsBean = getProcessData(processId);
		if(pcsBean == null){
			//出错
			DebugHelper.log("ProcessBase","getProcessData("+processId+") is null");
			return null;
		}
		
		//业务逻辑实际要处理的内容
		ProcessResult pcsResult = doProcessExt(msgBean);
		
		Integer nextProcessId = 0;
		if(pcsResult != null){
			if(pcsResult.getResult()){
				nextProcessId = pcsBean.getNextId();
			}else{
				nextProcessId = pcsBean.getNegativeId();
			}
			msg = pcsResult.getMsg();
		}else{
			DebugHelper.log("ProcessBase","doProcessExt() is null");
			return "系统错误，请联系管理员";
		}
		updateNextProcessId(msgBean.getFromUserName(),nextProcessId);
		ProcessBean nextPcsBean = getProcessData(nextProcessId);
		if(nextPcsBean.getTips() != null)
			msg += "\n" + nextPcsBean.getTips();
		return msg;
	}
	
	protected abstract ProcessResult doProcessExt(ReqMessageBean msgBean);

	private Integer processId;
	
	public void updateNextProcessId(String fromUserName,Integer processId){	
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		customerBean.setProcessId(processId);
		customerBean.setLasttime(new Date());
		customerBean.setProcessing(false);
		customerDao.saveCustomer(customerBean);
	}
	@Override
	public void setProcessId(Integer processId) {
		// TODO Auto-generated method stub
		this.processId = processId;
	}


	public ProcessBean getProcessData(int id){
		ProcessBeanDao pcsDao = new ProcessBeanDao();
		return pcsDao.getProcessBean(id);
	}
}
