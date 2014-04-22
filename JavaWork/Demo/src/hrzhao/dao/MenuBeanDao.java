package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import hrzhao.beans.MenuBean;
import hrzhao.utils.HiberHelper;

public class MenuBeanDao {

	public MenuBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public List<MenuBean> getMenuBeanList(int processId){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(MenuBean.class);
		cr.add(Restrictions.eq("processId",processId));
		cr.addOrder(Order.asc("selectItem"));
		@SuppressWarnings("unchecked")
		List<MenuBean> list = cr.list();
		session.close();
		return list;
	}

}
