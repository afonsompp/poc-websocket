package br.com.websocket.client.config;

import br.com.websocket.client.cert.CertUtils;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class WebSocketClientConfig {
	
	private final WebSocketHostnameVerifier hostnameVerifier;
	
	public WebSocketClientConfig(WebSocketHostnameVerifier hostnameVerifier) {this.hostnameVerifier = hostnameVerifier;}
	
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder()
				.sslSocketFactory(CertUtils.getSSLContext().getSocketFactory(), CertUtils.getTrustManager())
				.hostnameVerifier(hostnameVerifier)
				.retryOnConnectionFailure(true)
				.build();
	}
}
