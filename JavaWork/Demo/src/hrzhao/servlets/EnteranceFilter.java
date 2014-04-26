package hrzhao.servlets;

import java.util.Calendar;
import java.util.Date;
import java.util.List;








import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.MessageBeanDao;
import hrzhao.pcs.base.PcsFactory;
import hrzhao.pcs.base.PcsInterface;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.DebugHelper;

public class EnteranceFilter {

	public EnteranceFilter() {
		
	}
	private CustomerBean customer;
	public String filterMessage(ReqMessageBean msgBean) {
		now = new Date();
		if(checkRepeat(msgBean)){
			return null;
		}
		
		if(!checkMsgType(msgBean)){
			return "对不起，不能处理此类型消息!";
		}
		customer = getCustomer(msgBean.getFromUserName());
		if(!checkReady()){
			return null;
		};
		customer.setProcessing(true);
		saveCustomer();//save
		String msg = "";
		PcsInterface pcs = PcsFactory.createPcs(customer.getProcessId());
		if(pcs == null){//回PcsHOME
			DebugHelper.log("EnteranceFilter", "pcs is null");
			customer.setProcessId(ConfigHelper.homePcsId);
//			return null;//终止了后面的保存工作
		}else{
			pcs.setCustomer(customer);//
			
			if(!checkReturnHome(msgBean)){
				//或输入#则跳过doProcess
				msg = pcs.doProcess(msgBean);
			}else{
				pcs.setNextProcessId(ConfigHelper.homePcsId);
			}
			
			String nextTips = pcs.getNextTips();
			if(nextTips != null && !nextTips.equals("")){
				msg += "\n" + nextTips;
			}
			
			int nextProcessId = pcs.getNextProcessId();
			JSONObject pcsData = pcs.getProcessData();
			if(pcsData != null){
				customer.setProcessData(pcsData.toString());
			}else{
				customer.setProcessData(null);
			}
			customer.setProcessId(nextProcessId);
		}
		customer.setLasttime(now);
		customer.setProcessing(false);
		saveCustomer();//save
		return msg;
	}
	private Boolean checkReturnHome(ReqMessageBean msgBean){
		String content = msgBean.getContent();
		if(content != null && content.equals(ConfigHelper.returnSignal)){
			return true;
		}
		return false;
	}
	
	
	private void saveCustomer(){
		CustomerBeanDao customerDao = new CustomerBeanDao();
		customerDao.saveOrUpdateCustomer(customer);
	}
	private Date now;
	private Boolean checkReady(){
		Date lastTime = customer.getLasttime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastTime);
		calendar.add(Calendar.MINUTE, 5);
		Date bTime = calendar.getTime();
		Boolean timeOut = bTime.getTime() < now.getTime();
		if(timeOut){
			if(customer.getProcessId() != ConfigHelper.welPcsId){//
				customer.setProcessId(ConfigHelper.timeoutPcsId);
			}
		}else{
			if(customer.getProcessing()){
				//正在处理，发送消息的速度过快，不处理
				DebugHelper.log("MessageFilter.getCustomerProcessId()", "正在处理，发送消息的速度过快，不处理");
				return false;
			}
		}
		return true;
	}
	
	private CustomerBean getCustomer(String name){
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customer = customerDao.getCustomer(name);
		if(customer == null){
			customer = new CustomerBean();
			customer.setName(name);
			customer.setProcessId(ConfigHelper.welPcsId);
			customer.setIntime(new Date());
			customer.setLasttime(now);
			customer.setProcessing(false);
		}
		//选择性保存
		customerDao.saveOrUpdateCustomer(customer);
		return customer;
	}
	
	private Boolean checkMsgType(ReqMessageBean reqMessageBean){
		String msgType = reqMessageBean.getMsgType();
		List<String> msgTypeList = ConfigHelper.getMsgType();
		if(!msgTypeList.contains(msgType)){
			return false;
		}
		return true;
	}
	
	private Boolean checkRepeat(ReqMessageBean msgBean){
		Boolean result = false;
		MessageBeanDao messageDao = new MessageBeanDao();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(msgBean.getCreateTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date bTime = calendar.getTime();
		List<ReqMessageBean> list = messageDao.getMessage(msgBean.getMsgId(),bTime);
		if(list != null && list.size() > 0){
			result = true;
		}
		return result;
	}

}
