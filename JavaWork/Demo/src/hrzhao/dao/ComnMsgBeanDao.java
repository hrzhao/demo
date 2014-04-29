package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.ComnMsgBean;
import hrzhao.utils.HiberHelper;

public class ComnMsgBeanDao {

	public ComnMsgBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveMsg(ComnMsgBean comnMsg){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(comnMsg);
		tx.commit();
		session.close();
	}
	public Boolean hasUnReadMsg(String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ComnMsgBean.class);
		cr.add(Restrictions.eq("customerName", customerName))
			.add(Restrictions.eq("status",1))//1是回复的信息
			.addOrder(Order.asc("intime"))
			.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<ComnMsgBean> list =cr.list();
		if(list != null && list.size() >0 ){
			return true;
		}
		return false;
	}
	public ComnMsgBean readLastUnReadMsg(String customerName){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(ComnMsgBean.class);
		cr.add(Restrictions.eq("customerName", customerName))
			.add(Restrictions.eq("status",1))//1是回复的信息
			.addOrder(Order.desc("intime"))
			.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<ComnMsgBean> list =cr.list();
		ComnMsgBean comnMsg = null;
		if(list != null && list.size() >0 ){
			comnMsg = list.get(0);
			comnMsg.setStatus(2);
			session.update(comnMsg);
		}
		tx.commit();
		return comnMsg;
	}

}
