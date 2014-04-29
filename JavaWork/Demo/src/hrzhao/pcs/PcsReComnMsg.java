package hrzhao.pcs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import hrzhao.beans.ComnMsgBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.ComnMsgBeanDao;
import hrzhao.pcs.base.PcsBase;

public class PcsReComnMsg extends PcsBase {

	public PcsReComnMsg() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		if(comnMsgDao.hasUnReadMsg(getCustomer().getName())){
			goThisProcess();
			return "";
		}else{
			goNextId();
			return "没有未读回复";
		}
	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		ComnMsgBean comnMsg = comnMsgDao.readLastUnReadMsg(getCustomer().getName());
		String tips = "";
		if(comnMsg != null){
			tips += comnMsg.getUser().getRealname()+"["+dataFormate.format(comnMsg.getIntime())+"]：\n" 
					+ comnMsg.getContent();
			tips += "\n"+super.getTips();
		}else{
			tips += "没有未读回复";
		}
		return tips;
	}
	private DateFormat dataFormate = new SimpleDateFormat("MM/dd hh:mm");
	

}
