package hrzhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DebugHelper {

	public DebugHelper() {
		// TODO Auto-generated constructor stub
	}
	public static void log(String name,String message){
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			String sql = "INSERT INTO `debug`(time,name,message) values(now(),?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, message);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionHelper.close(conn);
		}
	}

}
