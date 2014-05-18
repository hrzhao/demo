package hrzhao.beans;

import java.io.Serializable;

public class WorkerBean implements Serializable {
	private int id;
	private String name;
	private int type;
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public WorkerBean() {
		// TODO Auto-generated constructor stub
	}

}
