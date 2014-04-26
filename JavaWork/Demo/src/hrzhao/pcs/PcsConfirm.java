package hrzhao.pcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import hrzhao.beans.OrderBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.OrderBeanDao;
import hrzhao.pcs.base.PcsBase;

public class PcsConfirm extends PcsBase {

	public PcsConfirm() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		return "";
	}
	private String[] status = OrderBean.STATUS;

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		OrderBeanDao orderDao = new OrderBeanDao();
		List<OrderBean> list = orderDao.getOrderByCustomerName(getCustomer().getName());
		if(list == null || list.size()<=0){
			//没有订单
			return "没有未完成的订单，欢迎下单，请输入 # 返回";
		}else{
			JSONObject processData = new JSONObject();
			List<HashMap<String, Integer>> selectList = new ArrayList<HashMap<String,Integer>>(); 
			String msg = "";
			Iterator<OrderBean> it = list.iterator();
			int i = 1;
			while(it.hasNext()){
				OrderBean order = it.next();
				msg += i+"、"+ order.getProduct().getName() 
						+ order.getAmount() +"桶，"
						+ status[order.getStatus()] 
						+ order.getIntime()+"\n";
				HashMap<String, Integer > hm = new HashMap<String,Integer>();
				hm.put("key", i);
				hm.put("value", order.getId());
				selectList.add(hm);
				processData.element("Selection", selectList);
				setNextProcessData(processData);
			}
			msg += super.getTips();
			return msg;
		}
		//"请输入序号确认订单"
	}
	

}
