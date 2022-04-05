package com.ms.edu.auth;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ms.edu.model.InfoMsg;
import com.ms.edu.sigin.UserRepository;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/authentication")

public class Authentication {
	
	@Autowired
	private UserRepository userRopo;

	public Authentication() {}
	
	
	@GetMapping(value = "/login", produces = "application/json")
	public ResponseEntity<InfoMsg> testAuth()
	{
		
		return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
				"Test Autenticazione Ok!"), HttpStatus.OK);
	}

}
