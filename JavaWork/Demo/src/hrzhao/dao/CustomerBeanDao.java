package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.CustomerBean;
import hrzhao.utils.HiberHelper;

public class CustomerBeanDao {

	public CustomerBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public CustomerBean getCustomer(String name){
		CustomerBean customerBean = null;
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		Criteria cr = session.createCriteria(CustomerBean.class);
		cr.add(Restrictions.eq("name", name));
		cr.setMaxResults(1);
		List<CustomerBean> rs = cr.list();
		if(!rs.isEmpty()){
			customerBean = rs.remove(0);
		}
		
		session.getTransaction().commit();
		session.close();
		return customerBean;
	}
	public void saveCustomer(CustomerBean customerBean){
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		session.save(customerBean);
		
		session.getTransaction().commit();
		session.close();
	}
	public void updateCustomer(CustomerBean customerBean){
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		session.update(customerBean);
		
		session.getTransaction().commit();
		session.close();
	}
	public List<CustomerBean> getCustomerList() {
		// TODO Auto-generated method stub
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		
		Criteria cr = session.createCriteria(CustomerBean.class);
		List<CustomerBean> customerList = cr.list();
		session.getTransaction().commit();
		session.close();
		return customerList;
	}

}
