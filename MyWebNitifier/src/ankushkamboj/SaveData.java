package ankushkamboj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/push")
public class SaveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	int i, r;

	public SaveData() {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("msg");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/push_notification",
							"root", "");
			Statement statement = conn.createStatement();
			ResultSet rst = statement
					.executeQuery("SELECT * FROM push_notification.test_notification");
			rst.last();
			i = rst.getInt(1);
			String query = "insert into push_notification.test_notification values(?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, i);
			ps.setString(2, message);
			r = ps.executeUpdate();
			response.getWriter()
					.println("You Successfully Stored Data at:" + r);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
