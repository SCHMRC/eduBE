package com.ms.edu.classroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.edu.model.YearEnum;
import com.ms.edu.teacher.Teacher;
import com.sun.istack.Nullable;

import lombok.Data;

@Table(name="CLASS")
@Entity
@IdClass(ClassroomID.class)

public class Classroom {
	
	@Column(name = "year")
	@Id
	private String a_year;
	
	@Column(name = "section")
	@Id
	private String b_section;
	

	
	
	
	
	@ManyToMany(mappedBy = "classroom")
	@Nullable
	List<Teacher> teachers = new ArrayList<>();
	
	public Classroom() {}



	public Classroom(String a_year, String b_section) {
		super();
		this.a_year = a_year;
		this.b_section = b_section;
	}



	public String getYear() {
		return a_year;
	}


	public String getSection() {
		return b_section;
	}


	public void setYear(String year) {
		this.a_year = year;
	}


	public void setSection(String section) {
		this.b_section = section;
	}

}
