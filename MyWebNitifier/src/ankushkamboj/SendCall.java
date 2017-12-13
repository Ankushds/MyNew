package ankushkamboj;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.json.JSONObject;

@WebServlet("/call")
public class SendCall extends HttpServlet {
	int id = 0;
	private static final long serialVersionUID = 1L;

	public SendCall() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Session> sessions = DeviceWebSocketServer.list;

		for (Session session : sessions) {
			try {
				id = new PushTo().getUserID(String.valueOf(session));
				if (id != 0) {
					JSONObject object = new JSONObject();
					object.put("Message", "Welcome from WebSocket server");
					object.put("Token", String.valueOf(id));
					session.getBasicRemote().sendText(object.toString());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().println("Message send successfully");
	}
}
