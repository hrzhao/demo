package hrzhao.beans;

import hrzhao.adapter.TimestampAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.txw2.annotation.XmlCDATA;


@XmlRootElement(name = "xml")
public class ReqMessageBean {
	private int id;
	private String toUserName;
	private String fromUserName;
	private Date createTime;
	private String msgType;
	private String content;
	
	private Long msgId;

	private int innerType;
	public int getInnerType() {
		return innerType;
	}

	public void setInnerType(int innerType) {
		this.innerType = innerType;
	}

	public String getToUserName() {
		return toUserName;
	}

	@XmlCDATA
	@XmlElement(name = "ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	@XmlCDATA
	@XmlElement(name = "FromUserName")
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

	@XmlCDATA
	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	@XmlCDATA
	@XmlElement(name = "Content")
	public void setContent(String content) {
		this.content = content;
	}

	public Long getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
