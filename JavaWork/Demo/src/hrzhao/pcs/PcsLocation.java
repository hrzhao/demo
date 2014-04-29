package hrzhao.pcs;

import java.util.Date;

import hrzhao.beans.LocationBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.LocationBeanDao;
import hrzhao.pcs.base.PcsBase;

public class PcsLocation extends PcsBase {

	public PcsLocation() {
		// TODO Auto-generated constructor stub
		String[] msgTypes = {"location"};
		this.availableMsgType = msgTypes;
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = "";
		LocationBean location = new LocationBean();
		location.setIntime(new Date());
		location.setCustomerName(getCustomer().getName());
		location.setLocation_label(msgBean.getLocationLabel());
		location.setLocation_scale(msgBean.getLocationScale());
		location.setLocation_x(msgBean.getLocationX());
		location.setLocation_y(msgBean.getLocationY());
		LocationBeanDao locationDao = new LocationBeanDao();
		locationDao.saveLocation(location);
		msg += "位置已保存\n";
		msg += location.getLocation_label();
		goNextId();
		return msg;
	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		return super.getTips();
	}

	
}
