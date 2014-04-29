package hrzhao.utils;

import hrzhao.beans.DebugBean;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DebugHelper {

	public DebugHelper() {
		// TODO Auto-generated constructor stub
	}
//	public static void log(String name,String message){
//		Connection conn = null;
//		try {
//			conn = ConnectionHelper.getConnection();
//			String sql = "INSERT INTO `debug`(time,name,message) values(now(),?,?);";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, name);
//			ps.setString(2, message);
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{
//			ConnectionHelper.close(conn);
//		}
//	}
	public static void log(String name,String message){
		System.out.println("["+name+"]"+message);
		Session session = HiberHelper.getSession();
		Transaction tx = session.beginTransaction();
		DebugBean debugBean = new DebugBean();
		debugBean.setMessage(message);
		debugBean.setName(name);
		debugBean.setTime(new Date());
		session.save(debugBean);
		tx.commit();
		session.close();
	}

}
