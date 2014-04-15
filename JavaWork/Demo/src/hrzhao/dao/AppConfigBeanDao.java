package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hrzhao.beans.AppConfigBean;
import hrzhao.utils.HiberHelper;

public class AppConfigBeanDao {

	public AppConfigBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public AppConfigBean getAppConfigBean(String appId){
		Session session = HiberHelper.getSession();
		AppConfigBean appBean = (AppConfigBean) session.get(AppConfigBean.class, appId);
		return appBean;
	}
	public List<AppConfigBean> getAppConfigBeanList(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AppConfigBean.class);
		List<AppConfigBean> result = cr.list();
		return result;
	}
	public void deleteAppConfigBeanByAppId(String appId){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		Object obj = session.get(AppConfigBean.class, appId);
		session.delete(obj);
		tx.commit();
	}

}
