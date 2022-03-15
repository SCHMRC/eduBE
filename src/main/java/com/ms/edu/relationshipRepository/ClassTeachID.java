package com.ms.edu.relationshipRepository;

import java.io.Serializable;
import java.util.Objects;

public class ClassTeachID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6268406059545518042L;
	private Long a_teacher_ID;
	private String c_section_ID;
	private String b_year_ID;
	

	public ClassTeachID() {	}

	public ClassTeachID(Long teacherID, String yearID, String sectionID) {
		super();
		this.a_teacher_ID = teacherID;
		this.c_section_ID = yearID;
		this.b_year_ID = sectionID;
	}

	public Long getTeacherID() {
		return a_teacher_ID;
	}

	public String getYearID() {
		return c_section_ID;
	}

	public String getSectionID() {
		return b_year_ID;
	}

	public void setTeacherID(Long teacherID) {
		this.a_teacher_ID = teacherID;
	}

	public void setYearID(String yearID) {
		this.c_section_ID = yearID;
	}

	public void setSectionID(String sectionID) {
		this.b_year_ID = sectionID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(b_year_ID, a_teacher_ID, c_section_ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassTeachID other = (ClassTeachID) obj;
		return Objects.equals(b_year_ID, other.b_year_ID) && Objects.equals(a_teacher_ID, other.a_teacher_ID)
				&& Objects.equals(c_section_ID, other.c_section_ID);
	}

}
