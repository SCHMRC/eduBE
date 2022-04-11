package com.ms.edu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ms.edu.classroom.Classroom;
import com.ms.edu.classroom.ClassroomID;
import com.sun.istack.Nullable;

import lombok.Data;

@Entity
@Data
@Table(name="STUDENT")

public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5974091338305441740L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "gender")
	private char gender;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "behaviour")
	private String behaviour;
	
	@Column(name = "course")
	private String course;
	
	@Column(name = "bes")
	private boolean bes;
	
	@ManyToOne
	@JoinTable(
			  name = "STUDENT_CLASS_JOIN_TABLE",
			  joinColumns = {@JoinColumn(name = "idFK", referencedColumnName = "id")},
			  inverseJoinColumns= {@JoinColumn(name = "yearFK", referencedColumnName = "year"), @JoinColumn(name = "sectionFK", referencedColumnName = "section")})
    Classroom classroom;
	

	public Student() {
		
	}

}
