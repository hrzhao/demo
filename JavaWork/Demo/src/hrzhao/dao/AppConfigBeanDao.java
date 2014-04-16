package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import hrzhao.beans.AppConfigBean;
import hrzhao.data.OrderColumn;
import hrzhao.utils.HiberHelper;

public class AppConfigBeanDao {

	public AppConfigBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveAppConfigBean(AppConfigBean appConfigBean){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(appConfigBean);
		tx.commit();
		session.close();
	}
	public AppConfigBean getAppConfigBean(String appId){
		Session session = HiberHelper.getSession();
		AppConfigBean appBean = (AppConfigBean) session.get(AppConfigBean.class, appId);
		return appBean;
	}
	public List<AppConfigBean> getAppConfigBeanList(List<OrderColumn> orderColumns){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AppConfigBean.class);
		if(orderColumns != null){
			for(OrderColumn orderColumn:orderColumns){
				if(orderColumn.getAscending()){
					cr.addOrder(Order.asc(orderColumn.getColumn()));
				}else{
					cr.addOrder(Order.desc(orderColumn.getColumn()));
				}
			}
		}
		//ascending
		cr.addOrder(Order.asc("priority"));
		List<AppConfigBean> result = cr.list();
		return result;
	}
	public void deleteAppConfigBeanByAppId(String appId){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		Object obj = session.get(AppConfigBean.class, appId);
		if(obj != null){
			session.delete(obj);
		}
		tx.commit();
	}

}
