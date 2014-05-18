package hrzhao.pcs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import hrzhao.beans.ComnMsgBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.ComnMsgBeanDao;
import hrzhao.pcs.base.PcsBase;
import hrzhao.utils.ConfigHelper;

public class PcsReComnMsg extends PcsBase {

	public PcsReComnMsg() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String content = msgBean.getContent();
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		if(comnMsgDao.unReadMsgNum(getCustomer().getName())>0){
			if("忽略所有".equals(content)){
				setNextProcessId(ConfigHelper.homePcsId);
				int num = comnMsgDao.ignoreAllReply(getCustomer().getName());
				return "忽略"+num+"条留言回复";
			}else{
				goThisProcess();
				return "";
			}
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
			int num = comnMsgDao.unReadMsgNum(getCustomer().getName());
			tips += "[剩"+num+"条未读]\n";
			tips += "-------------\n";
			tips += comnMsg.getUser().getRealname()+"["+dataFormate.format(comnMsg.getIntime())+"]：\n" 
					+ comnMsg.getContent();
			tips += "\n-------------";
			tips += super.getTips();
		}else{
			tips += "没有未读回复";
		}
		return tips;
	}
	private DateFormat dataFormate = new SimpleDateFormat("MM/dd hh:mm");
	

}
