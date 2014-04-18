package hrzhao.services;

import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.MessageBeanDao;
import hrzhao.utils.ResultObject;

import java.util.HashMap;
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
		List<ReqMessageBean> list= msgDao.getMessageList(null,null,-1,-1);
		return new ResultObject(ResultObject.SUCCESS, list);
	}
	public ResultObject getMessageBeanList(String fromUserName,String toUserName,int firstResult,int size){
		if(msgDao == null){
			createMsgDao();
		}
		List<ReqMessageBean> list= msgDao.getMessageList(fromUserName, toUserName, firstResult, size);
		HashMap<String , Object> data = new HashMap<String,Object>();
		int total =msgDao.getMessageList(fromUserName, toUserName, -1, -1).size();
		data.put("data", list);
		data.put("total", total);
		return new ResultObject(ResultObject.SUCCESS,data);
	}
	public ResultObject getUserMsgList(){
		if(msgDao == null){
			createMsgDao();
		}
		List<HashMap<String, Object>> list= msgDao.getUserMsgListByCall();
		return new ResultObject(ResultObject.SUCCESS, list);
	}
}
