import hrzhao.beans.RequestBean;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


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
		method1();
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
			jc = JAXBContext.newInstance(RequestBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			RequestBean reqBean = (RequestBean) u.unmarshal(new StringReader(source));
			System.out.println(reqBean);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
