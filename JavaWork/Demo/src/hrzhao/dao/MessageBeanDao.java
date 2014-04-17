package hrzhao.dao;

import java.util.HashMap;
import java.util.List;





import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.transform.Transformers;

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
	public List<ReqMessageBean> getMessageList(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ReqMessageBean.class);
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
//		@SuppressWarnings("unchecked")
//		List<Object> list = (List<Object>)query.list();
//		session.close();
//		return DataUtil.listToMap(list,columns);
	}
	public List<HashMap<String,Object>> getUserMsgListByCall(){
		Session session = HiberHelper.getSession();
		Query query = session.createSQLQuery("call proc_userMsgList_get()")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
//		ProcedureCall proceCall = session.createStoredProcedureCall("");
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>> list = (List<HashMap<String,Object>>)query.list();
		session.close();
		return list;
	}
//	private String[] columns = {
//			"name",
//			"amount",
//			"realname",
//			"toUserName",
//			"intime",
//			"lastTime"};
}
