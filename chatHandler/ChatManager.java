package chatHandler;

import serverCommunication.ServerInterface;

import java.util.LinkedList;
import java.util.Queue;

public class ChatManager {
	ServerInterface serverInterface;
	Queue<Message> sendQueue;
	ChatSender sender;
	
	public ChatManager(ServerInterface sInterface) {
		this.serverInterface = sInterface;
		sendQueue = new LinkedList<Message>();
		sender = new ChatSender(serverInterface, this);
		sender.start();
	}
	
	public void send(Message message) {
		sendQueue.add(message);
		synchronized(sender) {
			sender.notify();
		}
	}
	
	public Message retrieveSendMessage() {
		return sendQueue.poll();
	}
	
	public boolean containsMessage() {
		return !sendQueue.isEmpty();
	}
}
