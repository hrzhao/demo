package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.OrderBean;
import hrzhao.utils.HiberHelper;

public class OrderBeanDao {

	public OrderBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveOrder(OrderBean orderBean){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(orderBean);
		tx.commit();
		session.close();
	}
	public List<OrderBean> getOrderByCustomerName(String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(OrderBean.class);
		cr.add(Restrictions.eq("customerName",customerName));
		cr.add(Restrictions.le("status", 2));//已配送及之前的
		@SuppressWarnings("unchecked")
		List<OrderBean> list = cr.list();
		return list;
	}


}
