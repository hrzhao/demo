package hrzhao.services;

import java.util.Date;

import hrzhao.HiberHelper;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.MessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;

public class MessageFilter {
	private static String wechatName = null;
	private MessageFilter instance = null;
	public MessageFilter getInstance(){
		if(instance == null){
			instance = new MessageFilter();
		}
		return instance;
	}
	private static void saveWechatName(String name){
		if(wechatName == null || wechatName.equals("")){
			wechatName = name;
		}
	}
	public String receiveMessage(MessageBean messageBean){
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
			msg +="欢迎您，第一次发信给邑水一方人，\n";
		}
		HiberHelper.closeFactory();
		msg +="刚才的消息已收到，稍候会有人员跟进。";
		return msg;
	}

}