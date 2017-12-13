package ankushkamboj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PushTo {
	Connection conn;
	int i=0;

	public PushTo() {
	}

	public int getMyConnection(String session) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/push_notification",
							"root", "");
			Statement statement = conn.createStatement();
			ResultSet rst = statement
					.executeQuery("SELECT id FROM push_notification.test_notification");
			if (rst.last()) {
				i = rst.getInt(1);
				i = i + 1;
			}else{
				i = i+1;
			}
			
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO push_notification.test_notification VALUES (?,?,?)");
			stmt.setInt(1, i);
			stmt.setString(2, session);
			stmt.setBoolean(3, true);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("TheExceptionIs : " + e);
		} catch (SQLException e) {
			System.out.println("TheExceptionIs : " + e);

		}
		return i;
	}

	public void getUpdatedConnection(int userId, String session) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/push_notification",
							"root", "");
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE push_notification.test_notification SET session= ?, status= ? WHERE id= ?");
			stmt.setString(1, session);
			stmt.setBoolean(2, true);
			stmt.setInt(3, userId);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}
	
	public int getUserID(String s) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager
				.getConnection(
						"jdbc:mysql://localhost:3306/push_notification",
						"root", "");
		Statement statement = conn.createStatement();
		ResultSet rst = statement
				.executeQuery("SELECT id FROM push_notification.test_notification WHERE session = '" + s + "'");
		rst.last();
		i = rst.getInt(1);
		System.out.println("TheUserId : " + i);
		return i;
	}
	
	public void updateStatus(String session){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/push_notification",
							"root", "");
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE push_notification.test_notification SET status= ? WHERE session= ?");
			stmt.setBoolean(1, false);
			stmt.setString(2, session);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}

}
