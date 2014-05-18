package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import hrzhao.beans.OrdersBean;
import hrzhao.utils.HiberHelper;

public class OrdersBeanDao {

	public OrdersBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public List<?> getOrdersByStatus(int status,String orderNo){
		Session session = HiberHelper.getSession();
		//setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		session.beginTransaction();
		String sql = "Select * from v_orders Where 1=1 ";
		sql += " and status = :status ";
		if(orderNo != null && !orderNo.equals("")){
			sql += " and orderNo like :orNo ";
		}
		SQLQuery q = session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.setInteger("status", status);
		if(orderNo != null && !orderNo.equals("")){
			q.setString("orNo", "%"+orderNo+"%");
		}
		List<?> list =  q.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public Object getVOrders(int id){
		Session session = HiberHelper.getSession();
		//setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		String sql = "Select * from v_orders Where  id = :id  ";
		SQLQuery q = session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.setInteger("id", id);
		List<?> list =  q.list();
		session.close();
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}
	public void saveOrder(OrdersBean order){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		//setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		String sql = "call  generate_orderNo('EM')";
		SQLQuery q = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> list =  q.list();
		if(list != null && list.size()>0){
			order.setOrderNo(list.get(0));
		}
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
		session.close();
		return list;
	}
	public OrdersBean getOrderById(int id){
		Session session = HiberHelper.getSession();
		OrdersBean order = (OrdersBean) session.get(OrdersBean.class,id);
		session.close();
		return order;
	}


}
