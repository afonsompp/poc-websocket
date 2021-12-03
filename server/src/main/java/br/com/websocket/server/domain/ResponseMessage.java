package br.com.websocket.server.domain;

public class ResponseMessage {
	private String name;
	private String message;

	public ResponseMessage(){
	}
	
	public ResponseMessage(String name, String message) {
		this.name = name;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static ResponseMessage getSampleMessage(){
		return new ResponseMessage("Server", "Responding ping");
	}
}
