package hrzhao.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hrzhao.beans.LocationBean;
import hrzhao.utils.HiberHelper;

public class LocationBeanDao {

	public LocationBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveLocation(LocationBean location){
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		session.save(location);
		tx.commit();
		session.close();
		
	}

}
