package lobby;

public class Message {
	private String message;
	private String user;
	private boolean privateMessage;
	
	public Message(String message, String user) {
		this.message = message;
		this.user = user;
		this.privateMessage = true;
	}
	
	public Message(String message) {
		this.message = message;
		this.privateMessage = false;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUser() {
		return user;
	}
	
	public boolean isPrivate() {
		return privateMessage;
	}
}
