package hrzhao.servlets;

import hrzhao.DebugHelper;
import hrzhao.beans.RequestBean;
import hrzhao.utils.WeChatHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

/**
 * Servlet implementation class EnteranceServlet
 */
@WebServlet("/Enterance")
public class EnteranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnteranceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String signature = request.getParameter("signature");
		String echostr = request.getParameter("echostr");
		if(	timestamp != null &&
			nonce != null &&
			signature != null &&
			echostr != null
				){
			if(WeChatHelper.checkSafe(timestamp, nonce, signature)){
				PrintWriter pw = response.getWriter();
				pw.print(echostr);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(request.getInputStream());
		response.setContentType("application/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DebugHelper.log("dopost", "dopost");
		try {
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(RequestBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			RequestBean reqBean = (RequestBean) u.unmarshal(new StringReader(sb.toString()));
			// 3、判定用户是否发的是地理位置的PO，并查询天气
//			String content = getContent(reqBean);
			String content = "这是来息aliyun的消息";
			DebugHelper.log("reqBean.content",reqBean.getContent());
			// 4、创建一个回复消息
			jc = JAXBContext.newInstance(RequestBean.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
				@Override
				public void escape(char[] arg0, int arg1, int arg2, boolean arg3,
						Writer arg4) throws IOException {
					arg4.write(arg0, arg1, arg2);
				}
			});
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			RequestBean respBean = createRespBean(reqBean, content);
			m.marshal(respBean, out);
			out.flush();
		} catch (JAXBException e) {
			DebugHelper.log("JAXBException",e.toString());
			//
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}
	/**
	 * @param reqBean
	 * @param content
	 * @return
	 */
	private RequestBean createRespBean(RequestBean reqBean, String content) {
		RequestBean respBean = new RequestBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType("text");
		respBean.setCreateTime(new Date().getTime());
		respBean.setContent(content);
		return respBean;
	}

}
