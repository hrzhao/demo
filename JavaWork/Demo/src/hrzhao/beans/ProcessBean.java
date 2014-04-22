package hrzhao.beans;

public class ProcessBean {
	private int id;
	private Integer nextId;
	private Integer negativeId;
	private String tips;
	private Boolean useTips;
	private String className;
	private String param;
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public Boolean getUseTips() {
		return useTips;
	}


	public void setUseTips(Boolean useTips) {
		this.useTips = useTips;
	}


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getNextId() {
		return nextId;
	}


	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}


	public Integer getNegativeId() {
		return negativeId;
	}


	public void setNegativeId(Integer negativeId) {
		this.negativeId = negativeId;
	}


	public ProcessBean() {
		// TODO Auto-generated constructor stub
	}

}
