package hrzhao.pcs;

import hrzhao.beans.ReqMessageBean;
import hrzhao.pcs.base.PcsBase;
import hrzhao.utils.ConfigHelper;

public class PcsTimeOut extends PcsBase {

	public PcsTimeOut() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = "先前操作已超时，回到首页\n"
				+"任何状态下输入 "+ConfigHelper.returnSignal+" 则回到首页";
		goNextId();
		return msg;
	}
	
	

}
