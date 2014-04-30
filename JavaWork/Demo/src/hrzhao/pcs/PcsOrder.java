package hrzhao.pcs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import hrzhao.beans.AcountBean;
import hrzhao.beans.OrdersBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.AcountBeanDao;
import hrzhao.dao.OrdersBeanDao;
import hrzhao.pcs.base.PcsBase;
import hrzhao.utils.ConfigHelper;

public class PcsOrder extends PcsBase {

	public PcsOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = "";
		String content = msgBean.getContent();
		String[] result = getOrderStrArr(content);
		if(result == null){
			//重输
			setNextProcessId(this.getProcessId());
			return "输入格式不正确";
		}else{
			Boolean succ = false;
			String selectedProductNo = result[0];
			selectedProductNo = selectedProductNo.toUpperCase();
			int amount =  Integer.parseInt(result[1]);
			if(amount <= 0){
				setNextProcessId(this.getProcessId());
				return "数量不能小于0";
			}
			int selectedProductId = -1;
			JSONObject processData = this.getProcessData();
			JSONArray orderSelection = (JSONArray )processData.get("OrderSelection");
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = orderSelection.iterator();
			while(it.hasNext()){
				JSONObject item = it.next();
				if(selectedProductNo.equals((String)item.get("no"))){
					selectedProductId =Integer.parseInt((String)item.get("productId"));
					break;
				}
			}
			
			if(selectedProductId >= 0){
				AcountBeanDao acountDao = new AcountBeanDao();
				AcountBean acount = acountDao.getAcountByIdAndCustomer(selectedProductId,getCustomer().getName());
				if(acount != null){
					if(selectedProductId == acount.getProductId()){
						if(amount > acount.getAmount()){
							msg += "[余量不足]";
						}else{
							//处理定购
							acount.setAmount(acount.getAmount() - amount);
							acountDao.updateAcount(acount);
							OrdersBeanDao orderDao = new OrdersBeanDao();
							OrdersBean orders = new OrdersBean();
							orders.setAmount(amount);
							orders.setCustomerName(getCustomer().getName());
							orders.setIntime(new Date());
							orders.setProductId(acount.getProductId());
							orderDao.saveOrder(orders);
							succ = true;
							msg += "[订购成功]\n";
							msg += getCustomer().getRealname()+"("+getCustomer().getName()+")，\n"
									+ getCustomer().getAddress() +"\n"
									+ acount.getProduct().getName() +"，" 
									+acount.getProduct().getCapacity() + "L，" +amount +"桶\n"
									+ "订单号：" +orders.getOrderNo();
							}
					}
				}
				
			}else{
				msg += "没有此选项";
				
			}
			if(succ == false){
				setNextProcessId(this.getProcessId());
				if(msg != null && !msg.equals("")){
					msg = "[订购失败]\n" + msg;
				}else{
					msg = "[订购失败]";
				}
			}else{
				setProcessData(null);
				goNextId();
			}
			return msg;
		}
	}
	

	
	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		AcountBeanDao acountDao = new AcountBeanDao();
		List<AcountBean> list = acountDao.getAcountByCustomer(getCustomer().getName());
		String tips = "";
		JSONObject processData = new JSONObject();
		List<HashMap<String, String>> orderList = new ArrayList<HashMap<String,String>>(); 
		Boolean emptyAcount = true;
		if(list != null && list.size() >0){
			int no = 0;
			for(AcountBean acount:list){
				if(acount.getAmount()>0){
					emptyAcount=false; 
					//
					tips += String.valueOf((char)(no + 'A')) + "、" +acount.getProduct().getName()+"("+acount.getAmount()+")\n";
					HashMap<String, String> hm = new HashMap<String,String>();
					hm.put("no", String.valueOf((char)(no + 'A')));
					hm.put("productId",acount.getProduct().getId()+"");
					orderList.add(hm);
					no++;
				}
			}
		}
		processData.element("OrderSelection", orderList);
		setNextProcessData(processData);
		if(emptyAcount){
			tips += "您所有的水已配送完毕，欢迎增订，请输入 "+ConfigHelper.returnSignal+" 返回";
		}else{
			tips += super.getTips();
		}
		return tips;
	}
	
	private String[] getOrderStrArr(String src){
		String[] result = null;
		if(src == null){
			return result;
		}
		String reg="[a-zA-Z]{1}-[0-9]{1,2}";
		if(src.matches(reg)){
			result = src.split("-");
		}
		return result;
	}
	
	

}
