package hrzhao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public final class Config {
//	public static String URL="jdbc:mariadb://127.0.0.1:3306/test";
//	public final static String USERNAME="root";
//	public final static String PASSWORD="4589";
//	public final static String DRIVER="org.mariadb.jdbc.Driver";
	public static String getConfig(String name){
		Connection conn = null;
		String value ="";
		try {
			conn = ConnectionHelper.getConnection();
			String sql = "SELECT * FROM `config` where name = ? limit 1 ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				value = rs.getString("value");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionHelper.close(conn);
		}
		return value;
	}
}
