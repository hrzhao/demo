package hrzhao.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.UserBean;
import hrzhao.utils.HiberHelper;

public class UserBeanDao {
	public UserBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveUser(UserBean userBean){
		
	}
	public UserBean getUser(String username){
		UserBean userBean = null;
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(UserBean.class);
		cr.add(Restrictions.eq("username",username));
		cr.setMaxResults(1);
		List<UserBean> rs = cr.list();
		if(!rs.isEmpty()){
			userBean = rs.remove(0);
		}
		session.close();
		return userBean;
	}
	public ArrayList<UserBean> getUserList(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(UserBean.class);
		cr.setMaxResults(1);
		ArrayList<UserBean> rs = (ArrayList<UserBean>) cr.list();
		session.close();
		return rs;
	}

}
