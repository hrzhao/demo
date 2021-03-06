package hrzhao.process.base;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;

public interface ProcessInterface {
	public String doProcess(ReqMessageBean msgBean);
	public void setProcessId(Integer processId);
	public String getTips(CustomerBean customerBean);
	public JSONObject getProcessData();
	public void setProcessData(JSONObject data);
	public ProcessBean getPcsBean();
	public JSONObject getParam();

}
