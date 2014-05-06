package hrzhao.services;

import java.util.Date;
import java.util.List;

import hrzhao.beans.AcountBean;
import hrzhao.beans.ChargeBean;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProductBean;
import hrzhao.dao.AcountBeanDao;
import hrzhao.dao.ChargeBeanDao;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.dao.ProductBeanDao;
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
	public ResultObject getCustomerList(String name,String realname){
		CustomerBeanDao customerDao = new CustomerBeanDao();
		List<CustomerBean> list = customerDao.getCustomerList(name,realname);
		return new ResultObject(ResultObject.SUCCESS,list);
	}
	
	public ResultObject addCharge(String customerName,int amount,int productId,float price){
		AcountBeanDao acountDao = new AcountBeanDao();
		AcountBean acount = acountDao.getAcountByIdAndCustomer(productId, customerName);
		if(acount == null){
			acount = new AcountBean();
			acount.setProductId(productId);
			acount.setAmount(amount);
			acount.setCustomerName(customerName);
		}else{
			acount.setAmount(acount.getAmount() + amount);
		}
		
		ChargeBeanDao chargeDao = new ChargeBeanDao();
		ChargeBean charge = new ChargeBean();
		charge.setAmount(amount);
		charge.setIntime(new Date());
		charge.setProductId(productId);
		charge.setCustomerName(customerName);
		if(price >= 0){//负数侧自动计算
			charge.setPrice(price);
		}else{
			ProductBeanDao productDao = new ProductBeanDao();
			ProductBean product = productDao.getProductById(productId);
			charge.setPrice(product.getPrice() * amount);
		}
		acountDao.saveOrUpdateAcount(acount);
		chargeDao.saveCharge(charge);
		return new ResultObject(ResultObject.SUCCESS,null);
	}

}
