package com.ms.edu.teacher;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.edu.matter.ServiceResponce;
import com.ms.edu.model.InfoMsg;
import com.ms.edu.model.MatterRepo;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	private ServiceResponce serviceResponce;

	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private ServiceTeacher serviceTeacher;
	
	@Autowired
	private MatterRepo matterRepo;
	
	
	/*
	 * get all teachers in the db
	 * */

	@GetMapping()
	public List<Teacher> getTeachers(){
		/*temporaneo collegamentoal server python*/
		String uri = "http://127.0.0.1:8000";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    /**/
		return this.serviceTeacher.getAllTeachers();	
	}
	
	@GetMapping("{teacherId}")
	public Teacher getTeacher(@PathVariable String teacherId){
		return this.serviceTeacher.getTeacher(Long.parseLong(teacherId));	
	}
		
	/*
	 * insert more teacher in the db
	 * */

	@PostMapping(path = "/teachers")
	public ResponseEntity<InfoMsg> postTeachers(@RequestBody ArrayList<Teacher> teachers) {
		return serviceResponce.responce(!this.serviceTeacher.saveAll(teachers));
	}
	
	@PostMapping(path = "/teachers/remove")
	public ResponseEntity<InfoMsg> removeTeachers(@RequestBody ArrayList<Teacher> teachers) {
		return serviceResponce.responce(this.serviceTeacher.remove(teachers));
	}

}
