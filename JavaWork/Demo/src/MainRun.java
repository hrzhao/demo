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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
	
	public static void main(String[] args) {
		getRequest("101110101");
	}
	
	public static String getRequest(String cityCode){
		String urlStr = "http://m.weather.com.cn/data/"+cityCode+".html";
		String result = "";
        BufferedReader in = null;
		 try {
	            URL url = new URL(urlStr);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/xml");
	            Map<String, List<String>> map = con.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	            		con.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
                if (in != null) {
                    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
	        }
		 return result;
	}
	public static void method33(){
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
