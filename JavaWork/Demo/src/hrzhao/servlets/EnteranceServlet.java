package hrzhao.servlets;

import hrzhao.DebugHelper;
import hrzhao.beans.MessageBean;
import hrzhao.services.MessageFilter;
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
		try {
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(MessageBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			MessageBean reqBean = (MessageBean) u.unmarshal(new StringReader(sb.toString()));
			MessageFilter msgFilter = new MessageFilter();
			String msg = msgFilter.receiveMessage(reqBean);
			
			// 4、创建一个回复消息
			jc = JAXBContext.newInstance(MessageBean.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(CharacterEscapeHandler.class.getName(),
					new CharacterEscapeHandler() {
				@Override
				public void escape(char[] arg0, int arg1, int arg2, boolean arg3,
						Writer arg4) throws IOException {
					arg4.write(arg0, arg1, arg2);
				}
			});
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			
			MessageBean respBean = createRespBean(reqBean, msg);
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
	private MessageBean createRespBean(MessageBean reqBean, String content) {
		MessageBean respBean = new MessageBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType("text");
		respBean.setCreateTime(new Date());
		respBean.setContent(content);
		return respBean;
	}

}
