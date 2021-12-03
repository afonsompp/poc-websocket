package br.com.websocket.client.domain;

public class PingMessage {
	private String name;
	private String message;

	public PingMessage(){
	}
	
	public PingMessage(String name, String message) {
		this.name = name;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static PingMessage getSampleMessage(){
		return new PingMessage("Client", "ping");
	}
}
