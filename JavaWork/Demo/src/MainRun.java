import hrzhao.ConfigHelper;
import hrzhao.HiberHelper;
import hrzhao.beans.Event;
import hrzhao.beans.MessageBean;
import hrzhao.beans.TestBean;
import hrzhao.dao.MessageBeanDao;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


//import hrzhao.Config;
//from bedroom
public class MainRun {

	public MainRun() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		UserService userService = new UserService();
//		userService.checkUser("admin", "202cb962ac59075b964b07152d234b70");
//		System.out.print(Config.getConfig("token"));
//		System.out.println(WeChatHelper.checkSafe("token", "timestamp", "nonce", "signature"));
//		method4hibernate();
//		System.out.println(ConfigHelper.getConfig("token"));
//		method2saveConfig();
//		Date date = new Date(1348831860L);
//		Long dateL = date.getTime();
//		System.out.println(date);
//		System.out.println(dateL);
//		Session session = HiberHelper.getSession();
//		HiberHelper.closeSession(session);
//		HiberHelper.closeFactory();
//		session.beginTransaction();
//		MessageBeanDao msgDao = new MessageBeanDao();
//		ArrayList<MessageBean> msgList = msgDao.getMessageList();
//		System.out.println(msgList);
		method2saveConfig();
	}
	public static void method2saveConfig(){
		String source ="<xml>"+
				"<ToUserName><![CDATA[toUser]]></ToUserName>"+
				"<FromUserName><![CDATA[fromUser]]></FromUserName>"+ 
				"<CreateTime>1348831860</CreateTime>"+
				"<MsgType><![CDATA[text]]></MsgType>"+
				"<Content><![CDATA[this is a test]]></Content>"+
				"<MsgId>1234567890123456</MsgId>"+
				"</xml>";
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(MessageBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			MessageBean reqBean = (MessageBean) u.unmarshal(new StringReader(source));
			MessageBeanDao msgDao = new MessageBeanDao();
			msgDao.saveMessage(reqBean);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HiberHelper.closeFactory();
		
	}
	public static void method4hibernate(){
//		SessionFactory sessionFactory = new Configuration()
//		.configure() // configures settings from hibernate.cfg.xml
//		.buildSessionFactory();
//		Configuration cfn = new Configuration().configure();
//		StandardServiceRegistryBuilder srBuilder = new StandardServiceRegistryBuilder()
//		.applySettings(cfn.getProperties());
//		StandardServiceRegistry sr = srBuilder.build();
//		SessionFactory sessionFactory = cfn.buildSessionFactory(sr);
//		///////////
//		Session session = sessionFactory.openSession();
		Session session = HiberHelper.getSession();
		session.beginTransaction();
		session.save( new Event( "Our very first event!", new Date() ) );
		session.save( new Event( "A follow up event", new Date() ) );
		session.getTransaction().commit();
//		session.close();
		HiberHelper.closeSession(session);

		// now lets pull events from the database and list them
		session = HiberHelper.getSession();
        session.beginTransaction();
        List result = session.createQuery( "from Event" ).list();
		for ( Event event : (List<Event>) result ) {
			System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
		}
        session.getTransaction().commit();
        HiberHelper.closeSession(session);
        HiberHelper.closeFactory();
		///////////
	}

	public static void method1 (){
		//xml反序列化
		String source ="<xml>"+
				"<ToUserName><![CDATA[toUser]]></ToUserName>"+
				"<FromUserName><![CDATA[fromUser]]></FromUserName>"+ 
				"<CreateTime>1348831860</CreateTime>"+
				"<MsgType><![CDATA[text]]></MsgType>"+
				"<Content><![CDATA[this is a test]]></Content>"+
				"<MsgId>1234567890123456</MsgId>"+
				"</xml>";
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(MessageBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			MessageBean reqBean = (MessageBean) u.unmarshal(new StringReader(source));
			System.out.println(reqBean);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
