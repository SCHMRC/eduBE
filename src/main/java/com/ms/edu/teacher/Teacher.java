package com.ms.edu.teacher;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;



import com.ms.edu.model.Matter;
import com.sun.istack.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ms.edu.classroom.Classroom;


import lombok.Data;

//idTeacher: identificativo docente
//name: nome insegnante
//surname: cognome insegnate
//matter: materia insegnata

//@Data
@Entity
@Table(name="TEACHER")


public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1206064536473556554L;
	
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;
	

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "edumail", nullable = true)
	private String edumail;
	
	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "adress", nullable = true)
	private String adress;
	
	@ManyToMany
	@JoinTable(
			  name = "MATTER_TEACH", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "matter_id"))
    List<Matter> matters = new ArrayList<>();
	
	@ManyToMany
	@Nullable
	@JoinTable(
			  name = "CLASS_TEACH", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = {@JoinColumn(name = "year_id"), @JoinColumn(name = "section_id")})
    List<Classroom> classroom = new ArrayList<>();


	public Teacher() {}


	public Teacher(Long id2, String name2, String surname2) {
		this.setId(id2);
		this.setName(name2);
		this.setSurname(surname2);
	}
	
	public Teacher(Long id, String name, String surname, List<Matter> matters) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.matters = matters;
	}


	public Teacher(Long id, String name, String surname, List<Matter> matters, List<Classroom> classroom) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.matters = matters;
		this.classroom = classroom;
	}
	
	


	public Teacher(Long id, String name, String surname, String email, String edumail, String phone, String adress,
			List<Matter> matters, List<Classroom> classroom) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.edumail = edumail;
		this.phone = phone;
		this.adress = adress;
		this.matters = matters;
		this.classroom = classroom;
	}
	
	
	public Teacher(Long id, String name, String surname, String email, String edumail, String phone, String adress, List<Matter> matters) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.edumail = edumail;
		this.phone = phone;
		this.adress = adress;
		this.matters = matters;
	}
	
	public Teacher(Long id, String name, String surname, String email, String edumail, String phone, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.edumail = edumail;
		this.phone = phone;
		this.adress = adress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public List<Matter> getMatters() {
		return matters;
	}


	public List<Classroom> getClassroom() {
		return classroom;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public void setMatters(List<Matter> matters) {
		this.matters = matters;
	}


	public void setClassroom(List<Classroom> classroom) {
		this.classroom = classroom;
	}


	public String getEmail() {
		return email;
	}


	public String getEdumail() {
		return edumail;
	}


	public String getPhone() {
		return phone;
	}


	public String getAdress() {
		return adress;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setEdumail(String edumail) {
		this.edumail = edumail;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}
	


}
