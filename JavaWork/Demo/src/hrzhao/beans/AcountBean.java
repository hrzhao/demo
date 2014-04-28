package hrzhao.beans;

import java.io.Serializable;

public class AcountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerName;
	private int productId;
	private int amount;
	private CustomerBean customer;
	private ProductBean product;
	

	
	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public ProductBean getProduct() {
		return product;
	}


	public void setProduct(ProductBean product) {
		this.product = product;
	}


	public CustomerBean getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public AcountBean() {
		// TODO Auto-generated constructor stub
	}

}
