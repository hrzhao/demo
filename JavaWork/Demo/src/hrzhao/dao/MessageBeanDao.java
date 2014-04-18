package hrzhao.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import hrzhao.beans.ReqMessageBean;
import hrzhao.utils.HiberHelper;
public class MessageBeanDao {
//	public MessageBean messageBean;
	public MessageBeanDao() {
		
	}

	public void saveMessage(ReqMessageBean messageBean) {
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		session.save(messageBean);
		session.getTransaction().commit();
		session.close();
	}
	public List<ReqMessageBean> getMessage(Long msgId,Date beginTime){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ReqMessageBean.class);
		cr.add(Restrictions.gt("createTime", beginTime));
		@SuppressWarnings("unchecked")
		List<ReqMessageBean> list = (List<ReqMessageBean>)cr.list();
		return list;
	}
//	public List<ReqMessageBean> getMessageList(){
//		return getMessageList(null,null,-1,-1);oBx4Dt37J4GSXlt32V4zGf-EDQQM gh_5a402ba88fa0
//	}
	public List<ReqMessageBean> getMessageList(String fromUserName,String toUserName,int firstResult,int size){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ReqMessageBean.class);
		cr.addOrder(Order.desc("intime"));
		if(fromUserName != null)
			cr.add(Restrictions.eq("fromUserName", fromUserName));
		if(toUserName != null)
			cr.add(Restrictions.eq("toUserName", toUserName));
		if(firstResult >= 0 && size>0){
			cr.setFirstResult(firstResult);
			cr.setMaxResults(size);
		}
		@SuppressWarnings("unchecked")
		List<ReqMessageBean> list = (List<ReqMessageBean>)cr.list();
		session.close();
		return list;
	}
	public List<HashMap<String,Object>> getUserMsgList(){
		Session session = HiberHelper.getSession();
		String sql = "SELECT "+
						"c.`name`,COUNT(*) AS amount,c.realname,m.toUserName, "+
						"c.intime,max(m.createTime) as lastTime "+
						"FROM customer AS c INNER JOIN message AS m "+
						"ON c.`name` = m.fromUserName "+
						"GROUP BY c.`name` ;";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)query.list();
		session.close();
		return list;
	}
	public List<HashMap<String,Object>> getUserMsgListByCall(){
		Session session = HiberHelper.getSession();
		Query query = session.createSQLQuery("call proc_userMsgList_get()")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> list = (List<HashMap<String,Object>>)query.list();
		session.close();
		return list;
	}
}
