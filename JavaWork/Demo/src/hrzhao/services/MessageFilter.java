package hrzhao.services;

import java.util.Date;
import java.util.List;

import hrzhao.ConfigHelper;
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
	public String receiveMessage(ReqMessageBean reqMessageBean){
		String msg = "";
		String msgType = reqMessageBean.getMsgType();
		List<String> msgTypeList = ConfigHelper.getMsgType();
		if(msgTypeList.contains(msgType)){
			MessageBeanDao messageDao = new MessageBeanDao();
			messageDao.saveMessage(reqMessageBean);
			CustomerBeanDao customerDao = new CustomerBeanDao();
			CustomerBean customerBean = customerDao.getCustomer(reqMessageBean.getFromUserName());
			if(customerBean == null){
				customerBean = new CustomerBean();
				customerBean.setName(reqMessageBean.getFromUserName());
				customerBean.setIntime(new Date());
				customerBean.setLasttime(new Date());
				customerBean.setType(1);
				customerDao.saveCustomer(customerBean);
				msg +="欢迎您首次来到。";
			}
			String realname = customerBean.getRealname();
			if(realname == null || realname.equals("")){
				customerBean.setType(1);//没有名字
			}else{
				msg += realname+",您好\n";
			}
			switch(customerBean.getType())
			{
			case 0:
				msg += "有什么可以帮到您：\n";
			case 1:
				msg += "请输入你的名字\n";
				customerBean.setType(2);
				break;
			case 2:
				realname = reqMessageBean.getContent();
				//保存名字
				customerBean.setRealname(realname);
				msg += "系统保存您的名字为：" + realname+"\n";
				customerBean.setType(0);//回到主目录
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			}
			customerDao.saveCustomer(customerBean);
			HiberHelper.closeFactory();
		}else{
			msg = "对不起，我不能处理您的这类型消息";
		}
		return msg;
	}

}