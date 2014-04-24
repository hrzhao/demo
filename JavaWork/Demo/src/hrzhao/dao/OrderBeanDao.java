package hrzhao.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
