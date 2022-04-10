package com.ms.edu.teacher;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ms.edu.classroom.Classroom;
import com.ms.edu.classroom.ClassroomID;
import com.ms.edu.classroom.ClassroomRepo;
import com.ms.edu.model.Matter;
import com.ms.edu.model.MatterRepo;
import com.ms.edu.relationshipRepository.ClassTeachID;
import com.ms.edu.relationshipRepository.ClassTeachRepository;
import com.ms.edu.relationshipRepository.MatterTeachRepository;

@Service
public class ServiceTeacher{
	boolean flag;
	Teacher teacher;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private ClassTeachRepository classTeachRepo;
	
	@Autowired
	private MatterRepo matterRepo;
	
	@Autowired
	private ClassroomRepo classroomRepo;
	
	@Autowired
	private MatterTeachRepository MTrepo;
	
	
	public Teacher getTeacher(Long id) {
		
		List<ArrayList<Object>>  object = this.teacherRepo.getInfoTeacher(id);
		if(object.size() == 0) {
			object = this.teacherRepo.getInfoTeacherNullClass(id);
		}
		List<Classroom> classroom = new ArrayList<>();
		List<Matter> matter = new ArrayList<>();
		this.teacher = new Teacher();
		
		object.forEach(objTeach -> {
			this.flag = false;
			BigInteger _id = (BigInteger) objTeach.get(0);
			String _name = (String) objTeach.get(1);
			String _surname = (String) objTeach.get(2);
			String _adress = (String) objTeach.get(3);
			String _edumail = (String) objTeach.get(4);
			String _email = (String) objTeach.get(5);
			String _phone = (String) objTeach.get(6);
			if(teacher.getId() == null) {
				this.teacher = new Teacher(_id.longValue(),_name,_surname,_email,_edumail,_phone,_adress);	
			}
			String _matter = (String) objTeach.get(7);
			Matter obj_matter = new Matter(_matter);
			if(!matter.contains(obj_matter)) {
				matter.add(obj_matter);	
			}
			
			if(objTeach.size() > 8 ) {
				String _year = (String) objTeach.get(8);
				String _classroom = (String) objTeach.get(9);
					Classroom obj_classroom = new Classroom(_year, _classroom);
					classroom.forEach(elm ->{
						if(elm.getYear() ==  obj_classroom.getYear() && elm.getSection() ==  obj_classroom.getSection()){
							this.flag = true;
						}
						});
					if(!this.flag) {
						classroom.add(obj_classroom);
				}
				
			}
			
		
		});
		if(classroom.isEmpty()) {
			this.teacher = new Teacher(this.teacher.getId(),this.teacher.getName(),this.teacher.getSurname(),this.teacher.getEmail(),this.teacher.getEdumail(),this.teacher.getPhone(),this.teacher.getAdress(),matter);
		}
		else {this.teacher = new Teacher(this.teacher.getId(),this.teacher.getName(),this.teacher.getSurname(),this.teacher.getEmail(),this.teacher.getEdumail(),this.teacher.getPhone(),this.teacher.getAdress(),matter,classroom);}
		
		return teacher;
	}
	
	
	private List<Teacher> getAllInfoTeacher(){
		List<ArrayList<Object>> object = this.teacherRepo.getAllInfoTeacher();
		List<Teacher> teachers = new ArrayList<Teacher>();
		object.forEach(objTeach->{
			this.flag = false;
			BigInteger id = (BigInteger) objTeach.get(0);
			String name = (String) objTeach.get(1);
			String surname = (String) objTeach.get(2);
			String matter = (String) objTeach.get(3);
			String year = (String) objTeach.get(4);
			String classroom = (String) objTeach.get(5);
			Teacher teacher = new Teacher(id.longValue(),name,surname);
			teachers.forEach(element ->{
				if(element.getId() == teacher.getId()){
				this.flag = true;
			}});
			if(!this.flag){teachers.add(teacher);}
		});
		return teachers;
		
	}
	
	public List<Teacher> getAllTeachers(){
		List<ArrayList<Object>> object = this.teacherRepo.getAllTeachers();
		List<Teacher> teachers = new ArrayList<Teacher>();
		object.forEach(objTeach->{
			this.flag = false;
			BigInteger id = (BigInteger) objTeach.get(0);
			String name = (String) objTeach.get(4);
			String surname = (String) objTeach.get(6);
			Teacher teacher = new Teacher(id.longValue(),name,surname);
			teachers.forEach(element ->{
				if(element.getId() == teacher.getId()){
				this.flag = true;
			}});
			if(!this.flag){teachers.add(teacher);}
		});
		return teachers;
	}
	
	
	public boolean saveAll(List<Teacher> teachers){
		this.flag = false;    
		teachers.forEach(teach -> {
			// save the teacher if not exist
			if(this.teacherRepo.isExistTeacher(teach.getName(),teach.getSurname()) == null) {
				this.teacherRepo.saveTeacher(teach.getName(), teach.getSurname(),teach.getAdress(),teach.getEdumail(),teach.getEmail(),teach.getPhone());
			}
			// save the matter if not exist
			teach.getMatters().forEach(matter->{
				if(!this.matterRepo.existsById(matter.getMatter())) {
					this.matterRepo.saveMatter(matter.getMatter());
				}
				// save the relationship matter <-> teacher if not exist	
				Teacher teacher = new Teacher();
				teacher = this.teacherRepo.isExistTeacher(teach.getName(),teach.getSurname());
				if(this.MTrepo.isInRelationshipTM(teacher.getId(), matter.getMatter()) == null) {
					this.MTrepo.saveRelationshipTM(teacher.getId(), matter.getMatter());
				}
				
			});
			
			// save the class if not exist
			teach.getClassroom().forEach(classroom -> {
				ClassroomID classroomID = new ClassroomID(classroom.getYear(),classroom.getSection());
				if(!this.classroomRepo.existsById(classroomID)) {
					this.classroomRepo.saveClassroom(classroom.getYear(),classroom.getSection());	
				}
				// save the class if not exist
				Teacher teacher = new Teacher();
				teacher = this.teacherRepo.isExistTeacher(teach.getName(),teach.getSurname());
				if(this.classTeachRepo.selectClassTeachRelation(teacher.getId(), classroom.getYear(), classroom.getSection()) == null) {
				   this.classTeachRepo.saveClassTeachRelation(teacher.getId(), classroom.getYear(), classroom.getSection());
				}	
			});
									
		});
		return this.flag;
		
	}
	
	public boolean remove(List<Teacher> teachers) {
		this.flag = false;
		teachers.forEach(teach ->{
			long id = teach.getId();
			Integer x = this.teacherRepo.deleteRelTeacherClass(id);
			Integer y = this.teacherRepo.deleteRelTeacherMatter(id);
			if(x>=0 && y>=0) {
				this.teacherRepo.deleteById(id);
				this.flag = true;
			}	
		});
		//long id = 10;
		//ClassTeachID idT = new ClassTeachID(id,"PRIMA","A");
		//this.classTeachRepo.deleteById(idT);
		return this.flag;
		
	}
	

	

}
