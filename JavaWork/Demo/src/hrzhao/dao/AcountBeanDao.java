package hrzhao.dao;

import hrzhao.beans.AcountBean;
import hrzhao.utils.HiberHelper;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

public class AcountBeanDao {

	public AcountBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public List<AcountBean> getAcountByCustomer(String customerName){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AcountBean.class)
				.add(Restrictions.eq("customerName", customerName))
				.createCriteria("product","p",JoinType.LEFT_OUTER_JOIN)
				.addOrder(Order.desc("p.name"));
		@SuppressWarnings("unchecked")
		List<AcountBean> list = cr.list();
		session.close();
		return list;
	}

}
