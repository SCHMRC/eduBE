package com.ms.edu.classroom;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.edu.matter.ServiceResponce;
import com.ms.edu.model.InfoMsg;
import com.ms.edu.relationshipRepository.ClassTeachRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/classroom")
public class ClassroomController {
	
	@Autowired
	private ClassroomRepo classRepo;
	
	@Autowired
	private ClassTeachRepository ctRepo;
	
	@Autowired
	ServiceResponce serviceResponce;
	
	@DeleteMapping("{year}/{section}")
	ResponseEntity<InfoMsg> removeClass(@PathVariable String year, @PathVariable String section){
		try {
			ClassroomID classroom = new ClassroomID(year,section);
			this.ctRepo.removeClassTeachRelation(year, section);
			classRepo.deleteById(classroom);
			return serviceResponce.responce(true);
		} catch (Exception e) {
			return serviceResponce.responce(false);
		}
	}
	
	
	@PostMapping("/insert")
	ResponseEntity<InfoMsg> insertClass(@RequestBody List<Classroom> classroom){
		try {
			classRepo.saveAll(classroom);
			return serviceResponce.responce(true);
		} catch (Exception e) {
			return serviceResponce.responce(false);
		}
	}

	@GetMapping
	List<String> getSection(){
		return this.classRepo.getSection();
	}
	
	@GetMapping("/all")
	List<Classroom> getAllClass(){
		return this.classRepo.findAll();
	}

}
