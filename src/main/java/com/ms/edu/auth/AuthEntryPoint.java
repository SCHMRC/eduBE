package com.ms.edu.auth;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.util.Assert;

import lombok.SneakyThrows;
import lombok.extern.java.Log;



@Log
public class AuthEntryPoint extends BasicAuthenticationEntryPoint {
	
	
	private String REALM = "REAME";

	public AuthEntryPoint() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterPropertiesSet() {
		Assert.hasText(this.REALM, "realmName must be specified");
	}

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		String ErrMsg = "Userid e/o Password non corrette!";//messaggio inviato al frontend
		
		log.warning("Errore Sicurezza: " + authException.getMessage());//messaggio di log del beckend
		
		// Authentication failed, send error response.
		response.setContentType("application/json;charset=UTF-8"); //invio una risposta al frontend di tipo json
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //imposto codice errore '401' autenticazione errore!
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + ""); //aggiungo all'header di risposta un messaggio
		
		
		//metodi per il messsaggio da inviare al frontend
		PrintWriter writer = response.getWriter();
		writer.println(ErrMsg);
	}

	public String getRealmName() {
		return this.REALM;
	}

	public void setRealmName(String realmName) {
		this.REALM = realmName;
	}
	
	

}
