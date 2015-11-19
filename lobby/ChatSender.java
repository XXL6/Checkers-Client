package lobby;

import serverCommunication.ServerInterface;

public class ChatSender extends Thread {
	
	ServerInterface sInterface;
	ChatManager manager;
	
	public ChatSender(ServerInterface sInterface, ChatManager manager) {
		this.sInterface = sInterface;
		this.manager = manager;
	}
	
	public void run() {
		System.out.println("Chat Sender Running");
		while(true) {
			Message toSend = null;
			synchronized(this) {
				try {
					this.wait(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				if (manager.containsMessage()) {
					toSend = manager.retrieveSendMessage();
					if (!toSend.isPrivate()) {
						sInterface.sendMsg_All(toSend.getMessage());
					} else {
						sInterface.sendMsg(toSend.getUser(), toSend.getMessage());
					}
				}
			}
		}
	}
}
