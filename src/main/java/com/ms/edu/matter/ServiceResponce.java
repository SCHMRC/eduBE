package com.ms.edu.matter;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.edu.model.InfoMsg;

@Service
public class ServiceResponce {

	public ServiceResponce() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseEntity<InfoMsg> responce(boolean predicate) {
		if(!predicate) {
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					"Error responce!"), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					"Success"), HttpStatus.OK);
		}
	}

}
