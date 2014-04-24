package hrzhao.beans;

import java.io.Serializable;
import java.util.Date;

public class OrderBean implements Serializable {
	private int id;
	private int productId;
	private String customerName;
	private Date intime;
	private Integer workerId;
	private int amount; 
	private Date confirmTime;
	private ProductBean product;
	
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderBean() {
		// TODO Auto-generated constructor stub
	}

}
