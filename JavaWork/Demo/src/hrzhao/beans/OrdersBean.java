package hrzhao.beans;

import java.io.Serializable;
import java.util.Date;

public class OrdersBean implements Serializable {
	private int id;
	private int productId;
	private String customerName;
	private Date intime;
	private Integer workerId;
	private int amount; 
	private Date confirmTime;
	private ProductBean product;
	private int status;
	
	public static final String[] STATUS = {"未配送","正在配送","配送完成","已确认","已丢弃"};
	
	/**
	 * 0、新建
	 * 1、正在配送
	 * 2、配送完成
	 * 3、已确认
	 * 4、已丢弃
	 * @return
	 */
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public OrdersBean() {
		// TODO Auto-generated constructor stub
	}

}
