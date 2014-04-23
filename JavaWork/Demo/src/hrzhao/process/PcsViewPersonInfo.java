package hrzhao.process;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;
import hrzhao.utils.DebugHelper;

public class PcsViewPersonInfo extends ProcessBase {

	public PcsViewPersonInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		String msg = "";
		return new ProcessResult(msg, true);
	}

	@Override
	public String getTips(CustomerBean customerBean) {
		String tips = "";
		tips += super.getTips(customerBean);
		if(customerBean == null){
			DebugHelper.log("PcsViewPersonInfo", "doProcessExt()");
			return "[系统错误#3]";
		}
		if(!tips.equals("")){
			tips+="\n";
		}
		String realname = customerBean.getRealname();
		String phone = customerBean.getPhone();
		String address = customerBean.getAddress();
		String building = customerBean.getBuilding();
		String room = customerBean.getRoom();
		
		tips += "姓名：" + realname==null?"":realname +"\n";
		tips += "电话：" + phone==null?"":phone + "\n";
		tips += "地址：" + address==null?"":address + "\n";
		tips += "楼号：" + building==null?"":building + "\n";
		tips += "房号：" + room==null?"":room;
		return tips;
	}
	

}
