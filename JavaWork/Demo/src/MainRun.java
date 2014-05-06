import hrzhao.beans.AcountBean;
import hrzhao.beans.AppConfigBean;
import hrzhao.beans.ComnMsgBean;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.Event;
import hrzhao.beans.ProductBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.beans.TestBean;
import hrzhao.beans.UserBean;
import hrzhao.dao.AcountBeanDao;
import hrzhao.dao.AppConfigBeanDao;
import hrzhao.dao.ComnMsgBeanDao;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;
import hrzhao.dao.UserBeanDao;
import hrzhao.services.FrameService;
import hrzhao.services.MessageServices;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.DebugHelper;
import hrzhao.utils.HiberHelper;
import hrzhao.utils.HibernateSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;


//import hrzhao.Config;
//from bedroom
public class MainRun {
	public MainRun() {
		// TODO Auto-generated constructor stub
	}
	public static void method33(){
		String fieldName = "Realname";
		String content = "张三";
		String methodName = "set" + fieldName;
		CustomerBean customerBean = new CustomerBean();
		Method method = null;
		try {
			int a1 = 1;
			Integer a2 = 2;
			method = CustomerBean.class.getDeclaredMethod("setProcessId",Integer.class);
			method.invoke(customerBean,a1);
			method = CustomerBean.class.getDeclaredMethod("setType",int.class);
			method.invoke(customerBean,a2);
//			Method[] methods =  CustomerBean.class.getMethods();
//			for(int i=0; i<methods.length;i ++){
//				if(methodName.equals(methods[i].getName())){
//					method = methods[i];
//					method.invoke(customerBean,new Date());
//					break;
//				}
//			}
			
		} catch ( NoSuchMethodException |SecurityException e) {
			System.out.println(e);
//			DebugHelper.log("PcsPersonInfo", "doProcessExt()1\n" + e.toString());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.println(e);
//			DebugHelper.log("PcsPersonInfo", "doProcessExt()2\n" + e.toString());
		}
	}
	public static void method55(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(AcountBean.class);
//		cr.add(Restrictions.eq("customerName","aa"));
		cr.add(Restrictions.eq("id",1));
		@SuppressWarnings("unchecked")
		List<AcountBean> list = cr.list();
		AcountBean acountBean = list.get(0);
		ProductBean product = acountBean.getProduct();
		System.out.println(product.getName());

//		acountBean = list.get(1);
//		product = acountBean.getProduct();
//		Hibernate.initialize(product);
//		System.out.println(product.getName());
//		System.out.println(product);
		System.out.println(list);
//		return product;
	}

	public static void method44(){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(CustomerBean.class);
		cr.add(Restrictions.eq("name", "aa"));
		@SuppressWarnings("unchecked")
		List<CustomerBean> list = cr.list();
		if(list != null && list.size() >0 ){
			CustomerBean customerBean = list.get(0);
			Set<AcountBean> acountList = customerBean.getAcount();
			System.out.println(customerBean.getRealname());
			Iterator<AcountBean> it = acountList.iterator();
			while(it.hasNext()){
				AcountBean acountBean = it.next();
				System.out.println(acountBean.getAmount());
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(new CustomerBeanDao().getCustomerList(null, "小"));
//		List<Object> list = new ComnMsgBeanDao().getComnMsgList(null, null, null, 1, 2);
//		Session session = HiberHelper.getSession();
//		String sql = "SELECT * FROM v_comnmsg where id = 7";
//		Query q = session.createSQLQuery(sql);
//		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		List<?> list = q.list();
//		System.out.println(list.size());
//		
//		@SuppressWarnings("unchecked")
//		List<String> list =  q.list();
//		if(list != null && list.size()>0){
//			System.out.println(list.get(0));
//		}
//		session.close();
//		int i = Integer.parseInt("123a");
//		JSONObject obj = new JSONObject();
//		Object o = obj.get("k");
//		Session session = HiberHelper.getSession();
//		session.get(ComnMsgBean.class, 1);
//		method55();
//		AcountBeanDao acountDao = new AcountBeanDao();
//		List<AcountBean> list = acountDao.getAcountByCustomer("aa");
//		System.out.println(list.size());
//		AcountBean acount = acountDao.getAcountByIdAndCustomer(1, "aa");
//		System.out.println(acount.getAmount());
//		String productNo = "B";
//		JSONObject jObj = JSONObject.fromObject("{\"OrderSelection\":[{\"no\":\"A\",\"productId\":\"1\"},{\"no\":\"B\",\"productId\":\"2\"}]}");
//		JSONArray jArr = (JSONArray )jObj.get("OrderSelection");
//		Iterator<JSONObject> it = jArr.iterator();
//		int selectedProductId = -1;
//		while(it.hasNext()){
//			JSONObject item = it.next();
//			if(productNo.equals((String)item.get("no"))){
//				//
//				selectedProductId =Integer.parseInt((String)item.get("productId"));
//				break;
//			}
//		}
//		if(selectedProductId >= 0){
//			System.out.println("已定购");
//		}else{
//			System.out.println("无此选项");
//			
//		}
//		
//		AcountBeanDao acountDao = new AcountBeanDao();
//		List<AcountBean> acountList = acountDao.getAcountByCustomer("aa");
//		AcountBean acount = acountList.get(0);
//		System.out.println(acount.getProduct().getName());
//		acount.getProduct().setName("kk");
//		acount.setAmount(acount.getAmount()-1);
//		acountDao.updateAcount(acount);
		
//		String src ="A-1";
//		getOrderStrArr(src);
//		List<AcountBean> list = new AcountBeanDao().getAcountByCustomer("aa");
//		for(AcountBean acount:list){
//			System.out.println(acount.getProduct().getName());
//		}
//		String str = "b";
//		String str = "a-1-2-a-";
//		String[] arr = str.split("-");
//		for(int i = 0; i<arr.length ;i++){
//			System.out.println(arr[i]);
//		}
//		System.out.println((str -1));
//		char c = 'D';
//		String s = 'D';
//		int i = Integer.valueOf(s);
//		System.out.println(Integer.valueOf(s));
//		method33();
//		JSONObject jsonObj =JSONObject.fromObject("{\"name\":\"json\",\"bool\":true1");
//		System.out.println(jsonObj.toString());
//		if(jsonObj.containsKey("name")){
//			System.out.println(jsonObj.get("name"));
//		}
//		HttpPostTest postTest = new HttpPostTest();
//		String urlStr = "http://localhost:8080/Demo/Enterance";
//		String content = "1";
//		postTest.testPost(urlStr, "hi",1007);
//		postTest.testPost(urlStr, "1",1008);
//		postTest.testPost(urlStr, "1",1009);
//		postTest.testPost(urlStr, "张三",1010);
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
			e.printStackTrace();
		}
		
		
	}

}
