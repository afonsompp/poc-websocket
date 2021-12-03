package br.com.websocket.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Component
public class WebSocketHostnameVerifier implements HostnameVerifier {
	
	@Override public boolean verify(String s, SSLSession sslSession) {
		return true;
	}
}
