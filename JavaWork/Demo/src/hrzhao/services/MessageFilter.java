package hrzhao.services;

import java.util.Date;

import hrzhao.HiberHelper;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;

public class MessageFilter {
	private MessageFilter instance = null;
	public MessageFilter getInstance(){
		if(instance == null){
			instance = new MessageFilter();
		}
		return instance;
	}
	public String receiveMessage(ReqMessageBean messageBean){
		String msg = "";
		MessageBeanDao messageDao = new MessageBeanDao();
		messageDao.saveMessage(messageBean);
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(messageBean.getFromUserName());
		if(customerBean == null){
			customerBean = new CustomerBean();
			customerBean.setName(messageBean.getFromUserName());
			customerBean.setIntime(new Date());
			customerBean.setLasttime(new Date());
			customerDao.saveCustomer(customerBean);
			msg +="欢迎您，我是邑水一方人。";
		}
		HiberHelper.closeFactory();
		msg +="刚才的消息已收到，稍候会有人员跟进。";
		msg +="#"+messageBean.getContent();
		return msg;
	}

}