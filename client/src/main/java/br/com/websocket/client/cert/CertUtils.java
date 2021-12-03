package br.com.websocket.client.cert;

import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class CertUtils {
	
	public static SSLContext getSSLContext() {
		try {
			SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
			sslContextBuilder.loadTrustMaterial(new File("websocket.jks"), "123456".toCharArray());
			return sslContextBuilder.build();
		} catch (NoSuchAlgorithmException | CertificateException | KeyStoreException |
						 IOException | KeyManagementException e) {
			throw new RuntimeException("Error getting certificate");
		}
	}
	
	public static X509TrustManager getTrustManager(){
		return new TrustAllX509Manager();
	}
}
