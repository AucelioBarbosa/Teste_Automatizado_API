package br.com.south.core;

import io.restassured.http.ContentType;

public interface Constantes {
	
	String APP_BASE_URL = "http://localhost";
	Integer APP_PORT = 8080;
	String APP_BASE_PATH ="/api/v1";
	
	ContentType APP_CONTENT_TAPY = ContentType.JSON;
	
	Long MAX_TIMEOUT = 1000L;
}
