package hrzhao.services;

import java.util.List;

import hrzhao.beans.CustomerBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.utils.ResultObject;

public class CustomerService {

	public CustomerService() {
		// TODO Auto-generated constructor stub
	}
	public ResultObject getCustomerList(){
		ResultObject rso = new ResultObject();
		CustomerBeanDao customerDao = new CustomerBeanDao();
		List<CustomerBean> customerList = customerDao.getCustomerList();
		if(customerList != null){
			rso.setState(ResultObject.SUCCESS);
			rso.setData(customerList);
		}
		return rso;
		
	}

}
