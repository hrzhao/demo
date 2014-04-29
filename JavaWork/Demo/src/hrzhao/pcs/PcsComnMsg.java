package hrzhao.pcs;

import java.util.Date;

import hrzhao.beans.ComnMsgBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.ComnMsgBeanDao;
import hrzhao.pcs.base.PcsBase;

public class PcsComnMsg extends PcsBase {

	public PcsComnMsg() {
		String[] msgTypes = {"text","image"};
		this.availableMsgType = msgTypes;
		
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = "";
		ComnMsgBean comnMsg = new ComnMsgBean();
		comnMsg.setCustomerName(getCustomer().getName());
		comnMsg.setContent(msgBean.getContent());
		comnMsg.setIntime(new Date());
		comnMsg.setStatus(0);
		comnMsg.setMsgType(msgBean.getMsgType());
		comnMsg.setPicURL(msgBean.getPicURL());
		
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		comnMsgDao.saveMsg(comnMsg);
		msg ="留言已保存";
		goNextId();
		return msg;
	}
	

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		return super.getTips();
	}
	

}
