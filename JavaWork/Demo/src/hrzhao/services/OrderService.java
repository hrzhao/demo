package hrzhao.services;

import hrzhao.dao.OrdersBeanDao;
import hrzhao.utils.ResultObject;

public class OrderService {

	public OrderService() {
		// TODO Auto-generated constructor stub
	}
	public ResultObject getOrdersList(int status,String orderNo){
		if(status<0){
			status = 0;
		}
		return new ResultObject(ResultObject.SUCCESS,
				new OrdersBeanDao().getOrdersByStatus(status, orderNo));
	}

}
