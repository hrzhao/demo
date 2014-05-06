package hrzhao.beans;

import java.util.Date;

public class ChargeBean {
	private int id;
	private String customerName;
	private int productId;
	private Date intime;
	private float price;
	private int amount;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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


	public Date getIntime() {
		return intime;
	}


	public void setIntime(Date intime) {
		this.intime = intime;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public ChargeBean() {
		// TODO Auto-generated constructor stub
	}

}
