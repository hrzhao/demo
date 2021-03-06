package hrzhao.pcs;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import hrzhao.beans.ReqMessageBean;
import hrzhao.pcs.base.PcsBase;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.DebugHelper;

public class PcsWelcome extends PcsBase {

	public PcsWelcome() {
		// TODO Auto-generated constructor stub
		this.availableMsgType = ConfigHelper.msgType;
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		super.doProcess(msgBean);
		String msg = "";
		JSONObject param = getParam();
		if(param != null){
			try{
				msg = param.getString("message");
				if(msg == null){msg = "";}
			}catch(JSONException e){
				DebugHelper.log("PcsWelcome", "doProcess" + e.toString());
			}
		}
		return msg;
	}
	

}
