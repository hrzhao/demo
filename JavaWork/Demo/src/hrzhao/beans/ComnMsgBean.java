package hrzhao.beans;

import java.util.Date;

public class ComnMsgBean {
	private int id;
	private String customerName;
	private String msgType;
	private Integer userId;
	private Date intime;
	private int status;
	private String content;
	private String picURL;
	private UserBean user;
	
	

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



	public String getMsgType() {
		return msgType;
	}



	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Date getIntime() {
		return intime;
	}



	public void setIntime(Date intime) {
		this.intime = intime;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPicURL() {
		return picURL;
	}



	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}



	public UserBean getUser() {
		return user;
	}



	public void setUser(UserBean user) {
		this.user = user;
	}



	public ComnMsgBean() {
		// TODO Auto-generated constructor stub
	}

}
