package hrzhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import hrzhao.ConnectionHelper;
import hrzhao.beans.MessageBean;

public class MessageBeanDao {
//	public MessageBean messageBean;

	public MessageBeanDao() {
		// TODO Auto-generated constructor stub
	}

	public void saveMessage(MessageBean messageBean) {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			String sql = "INSERT INTO `message`("+
					"`toUserName`,"+
					"`fromUserName`, "+
					"`createTime`,"+
					"`content`,"+
					"`msgId`,"+
					"`msgType`,"+
					"`innerType`,"+
					") values(?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, messageBean.getToUserName());
			ps.setString(2, messageBean.getFromUserName());
			ps.setString(3, messageBean.getCreateTime().toString());
			ps.setString(4, messageBean.getMsgId().toString());
			ps.setString(5, messageBean.getMsgType());
			ps.setString(6, Integer.toString(messageBean.getInnerType()));
//			ps.setString(2, message);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(conn);
		}
	}
	public MessageBean getMessage(){
		return null;
	}
	public ArrayList<MessageBean> getMessageList(){
		
		return null;
	}

}
