package hrzhao.beans;

import java.io.Serializable;

public class Acount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerName;
	private int productId;
	private int amount;
	

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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


	public Acount() {
		// TODO Auto-generated constructor stub
	}

}
