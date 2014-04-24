package hrzhao.pcs.base;

import hrzhao.beans.CustomerBean;
import net.sf.json.JSONObject;

public class PcsBase implements PcsInterface {

	public PcsBase() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNextTips() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProcessId(int processId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCustomer(CustomerBean customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject getProcessData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProcessDate(JSONObject data) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject getParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextProcessId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
