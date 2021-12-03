package br.com.websocket.client;

import br.com.websocket.client.domain.PingMessage;
import br.com.websocket.client.domain.ReceivedMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.CertificatePinner;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebSocketSessionHandler extends WebSocketListener {
	
	private final Logger logger = LoggerFactory.getLogger(WebSocketSessionHandler.class);
	
	private final ObjectMapper objectMapper;
	
	public WebSocketSessionHandler(ObjectMapper objectMapper) {this.objectMapper = objectMapper;}
	
	@Override public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
		logger.info("Connection closed");
		super.onClosed(webSocket, code, reason);
	}
	
	@Override public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
		logger.info("Closing connection");
		super.onClosing(webSocket, code, reason);
	}
	
	@Override public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
		logger.info("Communication failure", t);
		super.onFailure(webSocket, t, response);
	}
	
	@Override public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
		try {
			ReceivedMessage receivedMessage = objectMapper.readValue(text, ReceivedMessage.class);
			logger.info("Message received: {}", receivedMessage);
			
			String response = objectMapper.writeValueAsString(PingMessage.getSampleMessage());
			webSocket.send(response);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		super.onMessage(webSocket, text);
	}
	
	@Override public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
		logger.info("Successful connected");
		super.onOpen(webSocket, response);
		response.close();
	}
}
