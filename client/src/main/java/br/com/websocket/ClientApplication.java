package br.com.websocket;

import br.com.websocket.client.WebSocketSessionHandler;
import br.com.websocket.client.domain.PingMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ClientApplication implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(ClientApplication.class);
	
	private final OkHttpClient client;
	private final WebSocketSessionHandler sessionHandler;
	private final ObjectMapper objectMapper;
	
	public ClientApplication(OkHttpClient client, WebSocketSessionHandler sessionHandler,
							 ObjectMapper objectMapper) {
		this.client = client;
		this.sessionHandler = sessionHandler;
		this.objectMapper = objectMapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	
	@Override public void run(String... args) throws Exception {
		Request request = new Request.Builder().url("wss://localhost:8080/chat").build();
		
		WebSocket session = client.newWebSocket(request, sessionHandler);
		String ping = objectMapper.writeValueAsString(PingMessage.getSampleMessage());
		logger.info("Send ping");
		
		for (int i = 0; i < 1500; i++) {
			session.send(ping);
		}
	}
}
