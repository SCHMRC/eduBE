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

import com.ms.edu.model.InfoMsg;
import com.ms.edu.model.MatterRepo;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

	
	
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
		return this.serviceTeacher.getAllTeachers();	
	}
	
	@GetMapping("{teacherId}")
	public Teacher getTeacher(@PathVariable String teacherId){
		return this.serviceTeacher.getTeacher(Long.parseLong(teacherId));	
	}
	
	/*
	 * insert one teacher in the db
	 * */
	
	@PostMapping()
	public Teacher postTeacher(@RequestBody Teacher teacher) {
		return this.teacherRepo.save(teacher);
		
	}
	
	/*
	 * insert more teacher in the db
	 * */

	@PostMapping(path = "/teachers")
	public ResponseEntity<InfoMsg> postTeachers(@RequestBody ArrayList<Teacher> teachers) {
		//teachers.forEach((teach)-> {this.teacherRepo.save(teach);});
		/*teachers.forEach(teac -> {
			this.teacherRepo.saveTeacher(teac.getName(), teac.getSurname());
			teac.getMatters().forEach(matter->{
				this.matterRepo.saveMatter(matter.getMatter());
				
			});
			}
		);*/
		if(this.serviceTeacher.saveAll(teachers)) {
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					"Teacher is already present!"), HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), 
					"Success"), HttpStatus.OK);
		}

	}

}
