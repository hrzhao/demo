import hrzhao.beans.AppConfigBean;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.Event;
import hrzhao.beans.ReqMessageBean;
import hrzhao.beans.TestBean;
import hrzhao.beans.UserBean;
import hrzhao.dao.AppConfigBeanDao;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;
import hrzhao.dao.UserBeanDao;
import hrzhao.services.FrameService;
import hrzhao.services.MessageFilter;
import hrzhao.services.MessageServices;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.HiberHelper;
import hrzhao.utils.HibernateSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;


//import hrzhao.Config;
//from bedroom
public class MainRun {

	public MainRun() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		HttpPostTest postTest = new HttpPostTest();
		String urlStr = "http://localhost:8080/Demo/Enterance";
		String content = "1";
		postTest.testPost(urlStr, content,1001);
//		
//		CustomerBean customerBean = new CustomerBeanDao().getCustomer("oBx4Dt37J4GSXlt32V4zGf-EDQQM");
//		System.out.println(customerBean.getRealname());
//		
//		oBx4Dt37J4GSXlt32V4zGf-EDQQM gh_5a402ba88fa0
//		new MessageBeanDao().getMessageList("oBx4Dt37J4GSXlt32V4zGf-EDQQM ",null, -1, -1);
//		Session session = HiberHelper.getSession();
//		UserBean ub = (UserBean) session.get(UserBean.class, 1);
//		System.out.println(ub.toString());
//		session.close();
//		
//		session = HiberHelper.getSession();
//		UserBean ub1 = (UserBean) session.get(UserBean.class, 1);
//		System.out.println(ub1.toString());
////		session1.close();
//		HiberHelper.closeFactory();
//		
//		Session session2 = HiberHelper.getSession();
//		UserBean ub2 = (UserBean) session2.get(UserBean.class, 1);
//		System.out.println(ub2.toString());
//		session2.close();
//		HiberHelper.closeFactory();
		
//		new MessageServices().getMessageBeanList();
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
//		session.close();
//		HiberHelper.closeFactory();
		
//		session.beginTransaction();
//		MessageBeanDao msgDao = new MessageBeanDao();
//		ArrayList<MessageBean> msgList = msgDao.getMessageList();
//		System.out.println(msgList);
//		method2saveConfig();
	
//		Date date = new Date();
//		System.out.print(date.getTime());
//		saveCustomer("ABCEDELS");
//		method3();
//		mthod5();
//		mthod6();
//		mthod7();
//		FrameService fs = new FrameService();
//		AppConfigBean appBean = new AppConfigBean();
//		appBean.setAppId("abc");
//		appBean.setName("abcname");
//		appBean.setCategoryId("ca");
//		appBean.setDescription("de");
//		appBean.setIcon("icon");
//		appBean.setMultiInstance(true);
//		appBean.setName("na");
//		appBean.setPriority(1);
//		appBean.setVisible(true);
//		appBean.setPath("pa");;
//		appBean.setParam("par");
//		fs.saveOrAddAppConfigBean(appBean);
//		fs.delAppConfigBeanByAppId("a");
//		AppConfigBean appBean;
//		AppConfigBeanDao appDao = new AppConfigBeanDao();
//		AppConfigBean appBean = appDao.getAppConfigBean("4d7665a6-175c-49e1-b89d-d8628157355e");
//		appBean = null;
//		UserBeanDao userDao = new UserBeanDao();
//		userDao.getUserList();
	}
	public static void mthod7(){
		MessageFilter msgFilter = new MessageFilter();
		ReqMessageBean reqBean = new ReqMessageBean();
		String msg = "";
		reqBean.setFromUserName("oBx4Dt37J4GSXlt32V4zGf-EDQQM");
		reqBean.setMsgType("text");
		
		reqBean.setCreateTime(new Date());
		reqBean.setContent("你好");
		msg += msgFilter.receiveMessage(reqBean);
		reqBean.setCreateTime(new Date());
		reqBean.setContent("赵海荣");
		msg += msgFilter.receiveMessage(reqBean);
		reqBean.setCreateTime(new Date());
		reqBean.setContent("松坪村");
		msg += msgFilter.receiveMessage(reqBean);
		System.out.println(msg);
		
		
//		return customerBean;
	}
	public static void mthod6(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		TestBean testBean = (TestBean) session.get(TestBean.class, 1);
		testBean.setName("li");
		testBean.setContent("liContent55");
		session.save(testBean);
		tx.commit();
		HibernateSessionFactory.closeSession();
		
		session = HibernateSessionFactory.getSession();
		tx = session.beginTransaction();
		testBean.setContent("liContent66");
		session.update(testBean);
		tx.commit();
		HibernateSessionFactory.closeSession();
	}
	public static void mthod5(){
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		TestBean testBean = new TestBean();
		testBean.setName("li");
		testBean.setContent("liContent");
		session.save(testBean);
		tx.commit();
		HibernateSessionFactory.closeSession();
	}
	public static void method4(){
		String[] msgT = {"event","image","text"};
		List<String> msgTypeList = new ArrayList<String>();
		for(String val:msgT){
			msgTypeList.add(val);
		}
		if(msgTypeList.contains("event")){
			System.out.println("hasEvent");
		}
		if(msgTypeList.contains("good")){
			System.out.println("good");
		}else{
			System.out.println("nogood");
			
		}
		
	}
	public static void method3(){
		StringReader sReader = new StringReader("asdsdfjljljsd你好吗中械的以要\n架困顺困右\n枯坷可顺在在中");
		BufferedReader bReader = new BufferedReader(sReader);
		
		String line = null;
		try {
			String sum ="";
			while((line = bReader.readLine()) != null ){
				System.out.println(line);
				sum += line;
			}
			System.out.println(sum);
			sum = "";
			System.out.println("2");
			while((line = bReader.readLine()) != null ){
				System.out.println(">>>>"+line);
				sum += line;
			}
			System.out.println(sum);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveCustomer(String customerName){
		
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(customerName);
		if(customerBean == null){
			customerBean = new CustomerBean();
			customerBean.setName(customerName);
			customerBean.setIntime(new Date());
			customerBean.setLasttime(new Date());
			customerDao.saveCustomer(customerBean);
		}
	}
	public static void method2saveConfig(){
		String source ="<xml>"+
				"<ToUserName><![CDATA[toUser]]></ToUserName>"+
				"<FromUserName><![CDATA[fromUser]]></FromUserName>"+ 
				"<CreateTime>1348831860</CreateTime>"+
				"<MsgType><![CDATA[text]]></MsgType>"+
				"<Content><![CDATA[this is a test支持中文吗？]]></Content>"+
				"<MsgId>1234567890123456</MsgId>"+
				"</xml>";
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(ReqMessageBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			ReqMessageBean reqBean = (ReqMessageBean) u.unmarshal(new StringReader(source));
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
			jc = JAXBContext.newInstance(ReqMessageBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			ReqMessageBean reqBean = (ReqMessageBean) u.unmarshal(new StringReader(source));
			System.out.println(reqBean);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
