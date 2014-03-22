package hrzhao.utils;

import java.sql.*;
//import org.mariadb.jdbc.Driver;
public class ConnectionHelper
{
	private String url;
	private String username;
	private String password;
	private static ConnectionHelper instance;

	private ConnectionHelper()
	{
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/test";
			username ="root";
			password = "4589";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url,instance.username,instance.password);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
