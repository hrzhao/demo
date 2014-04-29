package hrzhao.beans;

import java.util.Date;

public class LocationBean {
	private int id;
	private Date intime;
	private double location_x;
	private double location_y;
	private String customerName;
	private String location_label;
	private int location_scale;
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getIntime() {
		return intime;
	}



	public void setIntime(Date intime) {
		this.intime = intime;
	}



	public double getLocation_x() {
		return location_x;
	}



	public void setLocation_x(double location_x) {
		this.location_x = location_x;
	}



	public double getLocation_y() {
		return location_y;
	}



	public void setLocation_y(double location_y) {
		this.location_y = location_y;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getLocation_label() {
		return location_label;
	}



	public void setLocation_label(String location_label) {
		this.location_label = location_label;
	}



	public int getLocation_scale() {
		return location_scale;
	}



	public void setLocation_scale(int location_scale) {
		this.location_scale = location_scale;
	}



	public LocationBean() {
		// TODO Auto-generated constructor stub
	}

}
