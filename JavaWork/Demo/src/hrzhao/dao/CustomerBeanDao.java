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
	public void updateInfo(String fromUserName,String fieldName,Object value){
//		CustomerBean customerBean = getCustomer(fromUserName);
		//待完成
		
	}
	public CustomerBean getCustomer(String name){
		CustomerBean customerBean = null;
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(CustomerBean.class);
		cr.add(Restrictions.eq("name", name));
		cr.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<CustomerBean> rs = cr.list();
		if(!rs.isEmpty()){
			customerBean = rs.remove(0);
		}
		session.close();
		return customerBean;
	}
	public void saveOrUpdateCustomer(CustomerBean customerBean){
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		session.saveOrUpdate(customerBean);
		session.getTransaction().commit();
		session.close();
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
		@SuppressWarnings("unchecked")
		List<CustomerBean> customerList = cr.list();
		session.getTransaction().commit();
		session.close();
		return customerList;
	}
	public List<CustomerBean> getCustomerList(String name,String realname) {
		// TODO Auto-generated method stub
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(CustomerBean.class);
		if(name != null){
			cr.add(Restrictions.like("name", "%"+name+"%"));
		}
		if(realname != null){
			cr.add(Restrictions.like("realname",  "%"+realname+"%"));
		}
		@SuppressWarnings("unchecked")
		List<CustomerBean> customerList = cr.list();
		session.close();
		return customerList;
	}
	public CustomerBean getCustomerByName(String name) {
		// TODO Auto-generated method stub
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(CustomerBean.class);
		cr.add(Restrictions.eq("name", name));
		@SuppressWarnings("unchecked")
		List<CustomerBean> customerList = cr.list();
		CustomerBean customer = null;
		if(customerList != null && customerList.size()>0){
			customer =customerList.get(0);
		}
		session.close();
		return customer;
	}

}
