package hrzhao;

public class ResultObject {
	private String state;
	private Object data;
	private String message;
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	
	public ResultObject(String state, Object data) {
		super();
		this.state = state;
		this.data = data;
		this.message = "";
	}

	public ResultObject(String state, Object data, String message) {
		super();
		this.state = state;
		this.data = data;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResultObject() {
		// TODO Auto-generated constructor stub
	}

}
