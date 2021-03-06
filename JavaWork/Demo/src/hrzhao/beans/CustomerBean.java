package hrzhao.beans;

import java.util.Date;
import java.util.Set;

public class CustomerBean {
	private int id;
	private String name;
	private String realname;
	private int type;
	private String building;
	private String room;
	private String address;
	private Date intime;
	private String phone;
	private String sex;
	private Date lasttime;
	private Integer processId;
	private Boolean processing;
	private String processData;
	private Set<AcountBean> acount;
	
	public String getProcessData() {
		return processData;
	}

	public void setProcessData(String processData) {
		this.processData = processData;
	}

	public Set<AcountBean> getAcount() {
		return acount;
	}

	public void setAcount(Set<AcountBean> acount) {
		this.acount = acount;
	}

	public Boolean getProcessing() {
		return processing;
	}

	public void setProcessing(Boolean processing) {
		this.processing = processing;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public CustomerBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 1、首次到来
	 */
	public int getId() {
		return id;
	}
	/**
	 * 1、首次到来
	 * 2、输入realname
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

}
