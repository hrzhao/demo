package hrzhao.pcs;


import net.sf.json.JSONObject;
import hrzhao.beans.ReqMessageBean;
import hrzhao.pcs.base.PcsBase;

public class PcsWelcome extends PcsBase {

	public PcsWelcome() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		super.doProcess(msgBean);
		String msg = "";
		JSONObject param = getParam();
		if(param != null){
			msg = param.getString("welcomeMsg");
			if(msg == null){msg = "";}
		}
		return msg;
	}
	

}
