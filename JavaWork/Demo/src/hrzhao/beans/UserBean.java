package hrzhao.beans;

public class UserBean {
	public int id;
	public String username;
	public String password;
	public String realname;
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	public UserBean(int id, String username, String password, String realname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

}
