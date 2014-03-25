package hrzhao.dao;

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

}
