package hrzhao.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

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
	public Object sendMsg(String customerName,int userId,String content){
		ComnMsgBean comnMsg = new ComnMsgBean();
		comnMsg.setContent(content);
		comnMsg.setIntime(new Date());
		comnMsg.setCustomerName(customerName);
		comnMsg.setUserId(userId);
		comnMsg.setMsgType("text");
		comnMsg.setStatus(1);
		Session session = HiberHelper.getSession();
		Transaction  tx = session.beginTransaction();
		session.save(comnMsg);
		tx.commit();
		String sql = "SELECT * FROM v_comnmsg where id = " + comnMsg.getId();
		Query q = session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = q.list();
		session.close();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	private DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public List<Object> getComnMsgList(String customerName,Date beginTime,Date endTime,int firstResult,int size){
		Session session = HiberHelper.getSession();
		String sql = "SELECT * FROM v_comnmsg where 1=1 ";
		if(customerName != null){
			sql += " and customerName = '"+customerName+"' ";
		}
		if(beginTime != null){
			sql += " and beginTime > '"+dateFormate.format(beginTime)+"' ";
		}
		if(endTime != null){
			sql += " and endTime <= '"+dateFormate.format(endTime)+"' ";
		}
		Query q = session.createSQLQuery(sql);
		if(firstResult >= 0){
			q.setFirstResult(firstResult);
		}
		if(size > 0){
			q.setMaxResults(size);
		}			
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Object> list = q.list();
		session.close();
		return list;
	}


}
