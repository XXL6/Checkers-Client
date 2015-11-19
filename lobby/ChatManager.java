package lobby;

import serverCommunication.ServerInterface;

import java.util.LinkedList;
import java.util.Queue;

public class ChatManager {
	ServerInterface serverInterface;
	Queue<Message> sendQueue;
	ChatSender sender;
	Lobby lobby;
	
	public ChatManager(ServerInterface sInterface, Lobby lobby) {
		this.serverInterface = sInterface;
		sendQueue = new LinkedList<Message>();
		this.lobby = lobby;
		sender = new ChatSender(serverInterface, this);
		sender.start();
	}
	
	public void send(Message message) {
		sendQueue.add(message);
		synchronized(sender) {
			sender.notify();
		}
		if (!message.isPrivate()) {
			lobby.displayClientMessage(message);
		}
	}
	
	public Message retrieveSendMessage() {
		return sendQueue.poll();
	}
	
	public boolean containsMessage() {
		return !sendQueue.isEmpty();
	}
}
