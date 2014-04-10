package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

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

}
