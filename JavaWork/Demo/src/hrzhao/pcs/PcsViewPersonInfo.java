package hrzhao.pcs;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.pcs.base.PcsBase;
import hrzhao.utils.DebugHelper;

public class PcsViewPersonInfo extends PcsBase {

	public PcsViewPersonInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		return super.doProcess(msgBean);
	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		String tips = "";
		tips += super.getTips();
		CustomerBean customer = getCustomer();
		if(customer == null){
			DebugHelper.log("PcsViewPersonInfo", "getTips()");
			return "[系统错误]";
		}
		if(!tips.equals("")){
			tips+="\n";
		}
		String realname = customer.getRealname();
		String phone = customer.getPhone();
		String address = customer.getAddress();
		String building = customer.getBuilding();
		String room = customer.getRoom();
		String name = customer.getName();
		
		tips += "姓名：" + (realname==null?"":realname) +"\n";
		tips += "电话：" + (phone==null?"":phone) + "\n";
		tips += "地址：" + (address==null?"":address) + "\n";
		tips += "楼号：" + (building==null?"":building) + "\n";
		tips += "房号：" + (room==null?"":room) + "\n";
		tips += "内部码：" + (name==null?"":name);
		return tips;
		
		
	}

	
}
