package ankushkamboj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

@ServerEndpoint("/action/{user}")
public class DeviceWebSocketServer {
	public static List<Session> list = new ArrayList<>();
	JSONObject object;

	@OnOpen
	public void open(@PathParam("user") String userId, Session session)
			throws IOException {
		list.add(session);
		int storeId = Integer.valueOf(userId);
		if (userId.equals("0")) {
			System.out.println("TheWebSocketDataOpenIf:" + storeId);
			int uniqueToken = new PushTo().getMyConnection(String.valueOf(session));
		    object = new JSONObject();
			object.put("Message", "You successfully connected to Websocket server");
			object.put("Token", String.valueOf(uniqueToken));
			session.getBasicRemote().sendText(object.toString());
		} else {
			new PushTo().getUpdatedConnection(storeId, String.valueOf(session));
			object = new JSONObject();
			object.put("Message", "You successfully connected to Websocket server");
			object.put("Token", userId);
			session.getBasicRemote().sendText(object.toString());
		}
	}

	@OnClose
	public void close(Session session) {
		if (list.contains(session)) {
			new PushTo().updateStatus(String.valueOf(session));
			list.remove(session);
		}
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("TheWebSocketDataError:" + error);
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("TheWebSocketDataMessage:" + message);
	}
}
