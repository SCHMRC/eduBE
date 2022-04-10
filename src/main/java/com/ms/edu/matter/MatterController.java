package com.ms.edu.matter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.edu.model.InfoMsg;
import com.ms.edu.model.Matter;

@CrossOrigin("http://localhost:4200")
@RestController()
@RequestMapping("/api/matter")
public class MatterController {
	
	@Autowired
	private ServiceResponce serviceResponce;
	
	
	@Autowired
	private MatterService service;
	
	
	@PostMapping()
	public ResponseEntity<InfoMsg> addMatter(@RequestBody Matter matter) {
		return this.serviceResponce.responce(this.service.saveMatterTeacher(matter));
	}
	
	@DeleteMapping("{teacherId}/{matter}")
	public ResponseEntity<InfoMsg> removeMatter(@PathVariable String teacherId ,@PathVariable String matter) {
		return this.serviceResponce.responce(this.service.removeMatterTeacher(matter, Long.parseLong(teacherId)));
	}

}
