package hrzhao.beans;

import hrzhao.adapter.TimestampAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
public class TestBean {
	private int id;
	private String name;
	private Date createTime;
	
	public TestBean() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name = "CreateTime")
	@XmlJavaTypeAdapter(value = TimestampAdapter.class)
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}



	@XmlCDATA
	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
