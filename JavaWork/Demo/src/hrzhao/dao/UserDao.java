package hrzhao.dao;

import java.sql.*;
import java.util.ArrayList;

import hrzhao.ConnectionHelper;
import hrzhao.beans.UserBean;

public class UserDao {
	public UserDao() {
		// TODO Auto-generated constructor stub
	}
	public void saveUser(UserBean userBean){
		
	}
	public UserBean getUser(String username){
		Connection conn = null;
		UserBean userBean = null;
		try {
			conn = ConnectionHelper.getConnection();
			String sql = "SELECT * FROM user where username = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setUid(rs.getString("uid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionHelper.close(conn);
		}
		return userBean;
	}
	public ArrayList<UserBean> getUserList(){
		return null;
	}

}
