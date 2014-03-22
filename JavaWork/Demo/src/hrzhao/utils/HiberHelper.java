package hrzhao.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HiberHelper {
	private static SessionFactory sessionFactory = null;
	public HiberHelper() {
		// TODO Auto-generated constructor stub
	}
	public static Session getSession(){
		Session session = null;
		if(sessionFactory == null || sessionFactory.isClosed()){
			
			Configuration cfn = new Configuration().configure();
			StandardServiceRegistryBuilder srBuilder = new StandardServiceRegistryBuilder()
			.applySettings(cfn.getProperties());
			StandardServiceRegistry sr = srBuilder.build();
			sessionFactory = cfn.buildSessionFactory(sr);
			///////////
		}
		session = sessionFactory.openSession();
		return session;
	}
	public static void closeSession(Session session){
		if(session != null){
			session.close();
		}
	}
	public static void closeFactory(){
		if(sessionFactory != null){
			sessionFactory.close();
		}
		
	}

}
