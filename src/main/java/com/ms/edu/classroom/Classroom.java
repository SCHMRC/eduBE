package com.ms.edu.classroom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.edu.model.Student;
import com.ms.edu.model.YearEnum;
import com.ms.edu.teacher.Teacher;
import com.sun.istack.Nullable;

import lombok.Data;


@Entity
@Table(name="CLASS")
@IdClass(ClassroomID.class)

public class Classroom {
	
	@Id
	@Column(name = "year")
	private String a_year;
	
	@Id
	@Column(name = "section")
	private String b_section;
	
	
	@OneToMany
	@JoinTable(
			  name = "STUDENT_CLASS_JOIN_TABLE",
	          joinColumns = {@JoinColumn(name = "yearFK", referencedColumnName = "year"), @JoinColumn(name = "sectionFK", referencedColumnName = "section")},
	          inverseJoinColumns = {@JoinColumn(name = "idFK", referencedColumnName = "id")}
	  )
	List<Student> students = new ArrayList<>();
	

	
	@ManyToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
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
