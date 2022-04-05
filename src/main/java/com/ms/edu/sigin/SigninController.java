package com.ms.edu.sigin;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.edu.model.InfoMsg;
import com.ms.edu.model.Profile;
import com.ms.edu.model.User;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/signin")


public class SigninController {
	
	@Autowired
	private UtilityAuth util; 
	
	
	
	
	@PostMapping()
	private ResponseEntity<InfoMsg>  insertUser(@RequestBody LinkedHashMap<String, String> obj){
		try {
			this.util.insertUser(obj);
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					"Success!"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					e.getMessage()), HttpStatus.FORBIDDEN);
		}
		
	}
	

}
