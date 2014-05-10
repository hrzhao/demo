package hrzhao.dao;

import hrzhao.beans.AcountBean;
import hrzhao.utils.HiberHelper;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

public class AcountBeanDao {

	public AcountBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public List<AcountBean> getAcountByCustomer(String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AcountBean.class)
				.add(Restrictions.eq("customerName", customerName))
				.createCriteria("product","p",JoinType.LEFT_OUTER_JOIN)
				.addOrder(Order.desc("p.name"));
		@SuppressWarnings("unchecked")
		List<AcountBean> list = cr.list();
		session.close();
		return list;
	}
	public AcountBean getAcountByIdAndCustomer(int productId,String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AcountBean.class)
				.add(Restrictions.eq("customerName", customerName))
				.add(Restrictions.eq("productId",productId));
		@SuppressWarnings("unchecked")
		List<AcountBean> list = cr.list();
		session.close();
		AcountBean acount = null;
		if(list != null && list.size()>0){
			acount = list.get(0);
		}
		return acount;
	}
	public List<Object> getCustomerAcounts(String customerName){
		Session session = HiberHelper.getSession();
		String sql = "Select * from v_customer_acount where customerName = :customerName";
		Query q = session.createSQLQuery(sql);
		q.setString("customerName", customerName).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Object> list = q.list();
		session.close();
		return list;
	}
	public void updateAcount(AcountBean acount){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.update(acount);
		tx.commit();
		session.close();
	}
	public void saveOrUpdateAcount(AcountBean acount){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(acount);
		tx.commit();
		session.close();
	}

}
