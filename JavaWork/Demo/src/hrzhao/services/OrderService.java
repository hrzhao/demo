package hrzhao.services;

import org.hibernate.Session;

import hrzhao.beans.OrdersBean;
import hrzhao.dao.OrdersBeanDao;
import hrzhao.dao.WorkerBeanDao;
import hrzhao.utils.HiberHelper;
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
	public ResultObject getWorkerList(int type){
		return new ResultObject(ResultObject.SUCCESS,new WorkerBeanDao().getWorkerListByType(type));
	}
	public ResultObject changeOrder(int id,int status,int workerId){
		OrdersBeanDao ordersDao = new OrdersBeanDao();
		OrdersBean orders = ordersDao.getOrderById(id);
		if(orders != null){
			orders.setWorkerId(workerId);
			orders.setStatus(status);
			ordersDao.updateOrder(orders);
			return new ResultObject(ResultObject.SUCCESS,ordersDao.getVOrders(id));
		}
		return new ResultObject(ResultObject.FAIL,null);
	}

}
