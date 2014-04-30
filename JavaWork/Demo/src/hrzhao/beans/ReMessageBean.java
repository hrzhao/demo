package hrzhao.beans;

import hrzhao.adapter.CDataAdapter;
import hrzhao.adapter.TimestampAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class ReMessageBean {
	private int id;
	private String toUserName;
	private String fromUserName;
	private Date createTime;
	private String msgType;
	private String content;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToUserName() {
		return toUserName;
	}

	@XmlElement(name = "ToUserName")
	@XmlJavaTypeAdapter(value = CDataAdapter.class)
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	@XmlElement(name = "FromUserName")
	@XmlJavaTypeAdapter(value = CDataAdapter.class)
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	@XmlJavaTypeAdapter(value = TimestampAdapter.class)
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "MsgType")
	@XmlJavaTypeAdapter(value = CDataAdapter.class)
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	@XmlElement(name = "Content")
	@XmlJavaTypeAdapter(value = CDataAdapter.class)
	public void setContent(String content) {
		this.content = content;
	}

}
