package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
	public List<ProductBean> getProductList(int type){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ProductBean.class);
		cr.add(Restrictions.eq("type",type));
		@SuppressWarnings("unchecked")
		List<ProductBean> list = cr.list();
		session.close();
		return list;
	}

}
