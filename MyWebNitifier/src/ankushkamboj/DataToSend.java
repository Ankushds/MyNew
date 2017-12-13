package ankushkamboj;

import java.io.Serializable;

public class DataToSend implements Serializable {
	String message;
	String token;

	public DataToSend(String message, String token) {
		this.message = message;
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
