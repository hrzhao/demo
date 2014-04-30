package hrzhao.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hrzhao.beans.ReMessageBean;
import hrzhao.utils.HiberHelper;

public class ReMessageBeanDao {

	public ReMessageBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void save(ReMessageBean reMsg){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(reMsg);
		tx.commit();
		session.close();
	}

}
