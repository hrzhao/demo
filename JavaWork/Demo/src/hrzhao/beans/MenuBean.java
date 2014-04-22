package hrzhao.beans;

import java.io.Serializable;
public class MenuBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer processId;
	private Integer selectItem;
	private Integer mappingId;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Integer getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(Integer selectItem) {
		this.selectItem = selectItem;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public MenuBean() {
		// TODO Auto-generated constructor stub
	}

}
