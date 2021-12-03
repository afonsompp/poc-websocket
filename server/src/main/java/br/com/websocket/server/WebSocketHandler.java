package br.com.websocket.server;

import br.com.websocket.server.domain.ReceivedMessage;
import br.com.websocket.server.domain.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	private final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	
	private final ObjectMapper objectMapper;
	
	public WebSocketHandler(ObjectMapper objectMapper) {this.objectMapper = objectMapper;}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		ReceivedMessage receivedMessage = objectMapper.readValue(message.getPayload(), ReceivedMessage.class);
		logger.info("Message received: {}", receivedMessage);
		
		logger.info("Responding message");
		String response = objectMapper.writeValueAsString(ResponseMessage.getSampleMessage());
		session.sendMessage(new TextMessage(response));
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.info("new session established");
	}
}
