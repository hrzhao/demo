package hrzhao.pcs.base;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;


public interface PcsInterface {
	
	public String doProcess();
	public void setProcessId(int processId);
	public void setCustomer(CustomerBean customer);
	
	/**
	 * 来自CustomerBean.processData
	 * @return
	 */
	public JSONObject getProcessData();
	public void setProcessDate(JSONObject data);
	
	 /**
	  * 来自ProcessBean.param
	  * @return
	  */
	public JSONObject getParam();
	
	public int getNextProcessId();
	
	public String getTips();
	public String getNextTips();

}
