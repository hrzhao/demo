package hrzhao.pcs.base;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.utils.ConfigHelper;
import hrzhao.utils.DebugHelper;
import net.sf.json.JSONObject;

public class PcsBase implements PcsInterface {

	public PcsBase() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 流程主要方法
	 */
	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		this.nextProcessId = pcsBean.getNextId();
		return "";
	}
	
	@Override
	public void setNextProcessId(int nextProcessId){
		this.nextProcessId = nextProcessId;
	}
	private CustomerBean customer;
	protected CustomerBean getCustomer(){
		return this.customer;
	}
	
	/**
	 * 在PcsFatcory.createPcs()后调用一次
	 */
	@Override
	public void setCustomer(CustomerBean customer) {
		// TODO Auto-generated method stub
		this.customer = customer;
		if(this.customer != null){
			this.processData = getJsonObjectFromString(this.customer.getProcessData());
		}
	}

	private ProcessBean pcsBean;
	
	/**
	 * 在PcsFatcory.createPcs()内自动调用
	 */
	@Override
	public void setPcsBean(ProcessBean pcsBean) {
		// TODO Auto-generated method stub
		this.pcsBean = pcsBean;
		if(this.pcsBean != null){
			this.param = getJsonObjectFromString(pcsBean.getParam());
			this.processId = pcsBean.getId();
		}
	}
	private int processId;
	public int getProcessId(){
		return this.processId;
	}
	
	
	private JSONObject processData;

	@Override
	public JSONObject getProcessData() {
		// TODO Auto-generated method stub
		return processData;
	}

	private JSONObject param;
	@Override
	public JSONObject getParam() {
		// TODO Auto-generated method stub
		return param;
	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		String tips = null;
		if(pcsBean != null){
			tips = pcsBean.getTips();
		}
		return tips;
	}

	@Override
	public String getNextTips() {
		// TODO Auto-generated method stub
		String tips = "";
		PcsInterface nextPcs = PcsFactory.createPcs(nextProcessId);
		if(nextPcs == null){
			DebugHelper.log("PcsBase", "nextPcs is null");
			this.nextProcessId = ConfigHelper.homePcsId;
			tips += "[系统错误]";
			return "";
		}else{
			nextPcs.setCustomer(customer);
			if(this.nextProcessId != nextPcs.getProcessId()){
				this.nextProcessId = nextPcs.getProcessId();
				tips += "[系统错误，回到首页]\n";
			}
			tips += nextPcs.getTips();
			return tips; 
		}
	}
	
	
	
	
	private int nextProcessId;
	@Override
	public int getNextProcessId() {
		// TODO Auto-generated method stub
		return nextProcessId;
	}

	private JSONObject getJsonObjectFromString(String str){
		JSONObject jsonObj = null;
		if(str == null || str.equals(""))
			return jsonObj;
		try{
			jsonObj = JSONObject.fromObject(str);
		}catch(Exception e){
			DebugHelper.log("PcsBase", "getJsonObjectFromString()" + e.toString());
		}
		return jsonObj;
	}

}
