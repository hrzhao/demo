package hrzhao.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;

import hrzhao.HiberHelper;
import hrzhao.beans.MessageBean;
public class MessageBeanDao {
//	public MessageBean messageBean;
	

	public MessageBeanDao() {
		// TODO Auto-generated constructor stub
	}

	public void saveMessage(MessageBean messageBean) {
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		session.save(messageBean);
		
		session.getTransaction().commit();
		session.close();
	}
	public MessageBean getMessage(){
		return null;
	}
	public ArrayList<MessageBean> getMessageList(){
		ArrayList<MessageBean> messageList = null;
		Session session = HiberHelper.getSession();
		
		Criteria cr = session.createCriteria(MessageBean.class);
		messageList = (ArrayList<MessageBean>) cr.list();
		
		session.getTransaction().commit();
		session.close();
		return messageList;
	}

}
