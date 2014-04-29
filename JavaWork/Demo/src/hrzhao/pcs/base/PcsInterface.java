package hrzhao.pcs.base;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;


public interface PcsInterface {
	
	public String doProcess(ReqMessageBean msgBean);
	public void setCustomer(CustomerBean customer);
	public void setPcsBean(ProcessBean pcsBean);
	
	/**
	 * 来自CustomerBean.processData
	 * @return
	 */
	public JSONObject getProcessData();
	public void setProcessData(JSONObject processData);
	
	 /**
	  * 来自ProcessBean.param
	  * @return
	  */
	public JSONObject getParam();
	public int getNextProcessId();
	public void setNextProcessId(int nextProcessId);
	public String getTips();
	public String getNextTips();
	public int getProcessId();
	public JSONObject getNextProcessData();
	public String[] getAvailableMsgType();

}
