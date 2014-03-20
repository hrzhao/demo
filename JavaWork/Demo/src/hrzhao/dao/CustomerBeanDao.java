package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hrzhao.HiberHelper;
import hrzhao.beans.CustomerBean;

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
		
		session.close();
	}

}
