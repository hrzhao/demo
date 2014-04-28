package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.OrdersBean;
import hrzhao.utils.HiberHelper;

public class OrdersBeanDao {

	public OrdersBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveOrder(OrdersBean order){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		session.close();
	}
	public void updateOrder(OrdersBean order){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.update(order);
		tx.commit();
		session.close();
	}
	public List<OrdersBean> getOrderByCustomerName(String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(OrdersBean.class);
		cr.add(Restrictions.eq("customerName",customerName));
		cr.add(Restrictions.le("status", 2));//已配送及之前的
		@SuppressWarnings("unchecked")
		List<OrdersBean> list = cr.list();
		return list;
	}
	public OrdersBean getOrderById(int id){
		Session session = HiberHelper.getSession();
		OrdersBean order = (OrdersBean) session.get(OrdersBean.class,id);
		return order;
	}


}
