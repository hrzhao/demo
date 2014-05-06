package hrzhao.dao;

import org.hibernate.Session;

import hrzhao.beans.ProductBean;
import hrzhao.utils.HiberHelper;

public class ProductBeanDao {

	public ProductBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public ProductBean getProductById(int id){
		Session session = HiberHelper.getSession();
		ProductBean product = (ProductBean) session.get(ProductBean.class, id);
		session.close();
		return product;
	}

}
