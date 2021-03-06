package hrzhao.beans;

public class AppConfigBean {
	private String appId;
	private String name;
	private Boolean visible;
	private String categoryId;
	private String param;
	private Boolean multiInstance;
	private String description;
	private String path;
	private Integer priority;
	private String icon;
	private Boolean executePower = true;

	public AppConfigBean() {
		// TODO Auto-generated constructor stub
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Boolean getMultiInstance() {
		return multiInstance;
	}

	public void setMultiInstance(Boolean multiInstance) {
		this.multiInstance = multiInstance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getpriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getExecutePower() {
		return executePower;
	}

	public void setExecutePower(Boolean executePower) {
		this.executePower = executePower;
	}

	
	
}
