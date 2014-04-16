package hrzhao.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;

import hrzhao.beans.ReqMessageBean;
import hrzhao.utils.HiberHelper;
public class MessageBeanDao {
//	public MessageBean messageBean;
	

	public MessageBeanDao() {
		// TODO Auto-generated constructor stub
	}

	public void saveMessage(ReqMessageBean messageBean) {
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		session.save(messageBean);
		
		session.getTransaction().commit();
		session.close();
	}
	public ReqMessageBean getMessage(){
		return null;
	}
	public ArrayList<ReqMessageBean> getMessageList(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ReqMessageBean.class);
		return (ArrayList<ReqMessageBean>) cr.list();
		
	}

}
