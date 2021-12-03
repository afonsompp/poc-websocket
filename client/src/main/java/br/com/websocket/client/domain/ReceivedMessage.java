package br.com.websocket.client.domain;

public class ReceivedMessage {
	public String name;
	public String message;
	
	public ReceivedMessage() {
	}
	
	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override public String toString() {
		return "ReceivedMessage{" +
			   "name='" + name + '\'' +
			   ", message='" + message + '\'' +
			   '}';
	}
}
