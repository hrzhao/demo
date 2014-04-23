package hrzhao.servlets;

import hrzhao.beans.ReqMessageBean;
import hrzhao.beans.RespMessageBean;
import hrzhao.services.MessageFilter;
import hrzhao.utils.DebugHelper;
import hrzhao.utils.WeChatHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Date;

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
	@SuppressWarnings("finally")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String msg = "";
		try{			
			ReqMessageBean reqBean = getReqMessageBean(request);
			
			MessageFilter msgFilter = new MessageFilter();
			Long s = new Date().getTime();
			
//			msg = msgFilter.receiveMessage(reqBean);
			
			msg = msgFilter.receiveMessageByProcess(reqBean);
			if(msg == null){
				return;
			}
			Long e = new Date().getTime();
			
			msg +="\n{"+(e-s)+"}";
			
			RespMessageBean respBean = createRespBean(reqBean,msg);
			responseMessage(response, respBean);
		}catch(JAXBException e){
			DebugHelper.log("JAXBException",e.toString());
		}catch(Exception e){
			DebugHelper.log("UnknowException","doPost\n" + e.toString());
			e.printStackTrace();
		}
		finally{
			return;
		}
	}

	private ReqMessageBean getReqMessageBean(HttpServletRequest request) throws JAXBException{
		ReqMessageBean reqBean = null;
		
		JAXBContext jc = JAXBContext.newInstance(ReqMessageBean.class);
		Unmarshaller u = jc.createUnmarshaller();
		BufferedReader bReader;
		try {
			bReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(),"UTF-8")
					);
			String line = null;
			String srcXML = "";
			while((line = bReader.readLine())!=null){  
				srcXML += line;
			}
			//保存原消息
			DebugHelper.log("SrcXML",srcXML);
			reqBean = (ReqMessageBean) u.unmarshal(new StringReader(srcXML));
		} catch (IOException e) {
			DebugHelper.log("getReqMessageBean.IOException",e.toString());
			e.printStackTrace();
		}
		return reqBean;
	}
	
	private void responseMessage(HttpServletResponse response,RespMessageBean respBean) throws JAXBException{
		response.setContentType("application/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JAXBContext jc = JAXBContext.newInstance(RespMessageBean.class);
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
			m.marshal(respBean, out);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			DebugHelper.log("IOException",e.toString());
		} finally {
			if(out != null)
				out.close();
			
		}
	}
	/**
	 * @param reqBean
	 * @param content
	 * @return
	 */
	private RespMessageBean createRespBean(ReqMessageBean reqBean, String content) {
		RespMessageBean respBean = new RespMessageBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType("text");
		respBean.setCreateTime(new Date());
		respBean.setContent(content);
		return respBean;
	}

}
