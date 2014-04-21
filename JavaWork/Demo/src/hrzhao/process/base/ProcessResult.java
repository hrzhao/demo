package hrzhao.process.base;

public class ProcessResult {
	private String msg;
	private Boolean result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public ProcessResult(String msg,Boolean result) {
		this.msg = msg;
		this.result = result;
	}

}
