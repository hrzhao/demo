package hrzhao.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hrzhao.beans.ChargeBean;
import hrzhao.utils.HiberHelper;

public class ChargeBeanDao {

	public ChargeBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveCharge(ChargeBean charge){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(charge);
		tx.commit();
		session.close();
	}

}
