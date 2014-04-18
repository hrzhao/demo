package hrzhao.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.HiberHelper;

public class MessageFilter {
	private MessageFilter instance = null;
	public MessageFilter getInstance(){
		if(instance == null){
			instance = new MessageFilter();
		}
		return instance;
	}
	private Boolean checkRepeat(ReqMessageBean reqMessageBean){
		Boolean result = false;
		MessageBeanDao messageDao = new MessageBeanDao();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(reqMessageBean.getCreateTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date bTime = calendar.getTime();
		List<ReqMessageBean> list = messageDao.getMessage(reqMessageBean.getMsgId(),bTime);
		if(list != null && list.size() > 0){
			result = true;
		}
		return result;
	}
	public String receiveMessage(ReqMessageBean reqMessageBean){
		if(checkRepeat(reqMessageBean)){
			return null;
		}
		String msg = "";
		String msgType = reqMessageBean.getMsgType();
		Date now = new Date();
		reqMessageBean.setIntime(now);
		List<String> msgTypeList = ConfigHelper.getMsgType();
		if(msgTypeList.contains(msgType)){
			MessageBeanDao messageDao = new MessageBeanDao();
			messageDao.saveMessage(reqMessageBean);
			CustomerBeanDao customerDao = new CustomerBeanDao();
			CustomerBean customerBean = customerDao.getCustomer(reqMessageBean.getFromUserName());
			if(customerBean == null){
				customerBean = new CustomerBean();
				customerBean.setName(reqMessageBean.getFromUserName());
				customerBean.setIntime(now);
				customerBean.setLasttime(now);
				customerBean.setType(1);
				customerDao.saveCustomer(customerBean);
				msg +="欢迎您首次来到。\n";
			}
			String realname = customerBean.getRealname();
			String content = "";
			if(realname == null || realname.equals("")){
				if( customerBean.getType() !=2){
					customerBean.setType(1);//没有名字
				}else{
					//去2
				}
			}else{
				msg += realname+",您好\n";
			}
			content = reqMessageBean.getContent();
			switch(customerBean.getType())
			{
				case 0:
					msg += "有什么可以帮到您：\n";
					break;
				case 1:
					msg += "请输入您的名字:\n";
					customerBean.setType(2);
					break;
				case 2:
					//保存名字
					customerBean.setRealname(content);
					customerBean.setType(3);
					msg += "系统保存您的名字为：" + content+"，\n";
					msg += "请输入您的地址：\n";
					break;
				case 3:
					//保存地址
					msg += "系统保存您的地址为：" + content+"，\n";
					customerBean.setAddress(content);
					customerBean.setType(0);//回到主目录
					break;
				case 4:
					break;
				case 5:
					break;
			}
			customerBean.setLasttime(now);
			customerDao.updateCustomer(customerBean);
			HiberHelper.closeFactory();
		}else{
			msg = "对不起，我不能处理您的这类型消息";
		}
		return msg;
	}

}