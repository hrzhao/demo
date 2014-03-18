package hrzhao.beans;

public class UserBean {
	public int id;
	public String username;
	public String password;
	public String uid;
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	public UserBean(int id, String username, String password, String uid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uid = uid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

}
