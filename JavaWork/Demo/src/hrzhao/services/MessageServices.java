package hrzhao.services;

import hrzhao.beans.AppConfigBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.AppConfigBeanDao;
import hrzhao.dao.MessageBeanDao;
import hrzhao.utils.ResultObject;

import java.util.List;

public class MessageServices {
	private MessageBeanDao msgDao = null;
	public MessageServices() {
		// TODO Auto-generated constructor stub
		msgDao = new MessageBeanDao();
	}
	private void createMsgDao(){
		if(msgDao == null){
			msgDao = new MessageBeanDao();
		}
	}
	public ResultObject getMessageBeanList(){
		if(msgDao == null){
			createMsgDao();
		}
		List<ReqMessageBean> list= msgDao.getMessageList();
		return new ResultObject(ResultObject.SUCCESS, list);
	}
}
