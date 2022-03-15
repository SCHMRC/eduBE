package com.ms.edu.relationshipRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "CLASS_TEACH")
@IdClass(ClassTeachID.class)

public class ClassTeach {
	@Id
	@Column(name = "teacher_ID")
	private Long a_teacher_ID;
	
	@Id
	@Column(name = "section_ID")
	private String c_section_ID;
	
	@Id
	@Column(name = "year_ID")
	private String b_year_ID;
	

	public ClassTeach() {}

	public ClassTeach(Long teacherID, String yearID, String sectionID) {
		super();
		this.a_teacher_ID = teacherID;
		this.c_section_ID = sectionID;
		this.b_year_ID = yearID;
	}
	
	public Long getTeacherID() {
		return a_teacher_ID;
	}

	public String getYearID() {
		return b_year_ID;
	}

	public String getSectionID() {
		return c_section_ID;
	}

	public void setTeacherID(Long teacherID) {
		this.a_teacher_ID = teacherID;
	}

	public void setYearID(String yearID) {
		this.b_year_ID = yearID;
	}

	public void setSectionID(String sectionID) {
		this.c_section_ID = sectionID;
	}

}
