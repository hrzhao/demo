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
		int processId = customer.getProcessId();
		saveCustomer();
		
		PcsInterface pcs = PcsFactory.createPcs(customer.getProcessId());
		pcs.setProcessId(processId);
		pcs.setCustomer(customer);
		JSONObject pcsData = getJsonObjectFromString(customer.getProcessData());
		pcs.setProcessDate(pcsData);
		
		String msg = pcs.doProcess();
		
		pcsData = pcs.getProcessData();
		if(pcsData != null){
			customer.setProcessData(pcsData.toString());
		}else{
			customer.setProcessData("");
		}
		
		int nextProcessId = pcs.getNextProcessId();
		customer.setProcessId(nextProcessId);
		customer.setLasttime(now);
		customer.setProcessing(false);
		saveCustomer();
		return msg;
	}
	
	private JSONObject getJsonObjectFromString(String str){
		JSONObject jsonObj = null;
		if(str == null)
			return jsonObj;
		try{
			jsonObj = JSONObject.fromObject(str);
		}catch(Exception e){
			DebugHelper.log("EnteranceFilter", "getJsonObjectFromString()" + e.toString());
		}
		return jsonObj;
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
				customer.setProcessId(ConfigHelper.homePcsId);
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
