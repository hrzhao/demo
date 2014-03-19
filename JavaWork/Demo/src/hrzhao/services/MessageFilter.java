package hrzhao.services;

import hrzhao.beans.MessageBean;
import hrzhao.dao.MessageBeanDao;

public class MessageFilter {
	private MessageFilter instance = null;
	public MessageFilter getInstance(){
		if(instance == null){
			instance = new MessageFilter();
		}
		return instance;
	}
	public void receiveMessage(MessageBean messageBean){
		MessageBeanDao messageDao = new MessageBeanDao();
		messageDao.saveMessage(messageBean);
	}

}