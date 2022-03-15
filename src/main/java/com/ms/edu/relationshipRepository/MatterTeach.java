package com.ms.edu.relationshipRepository;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "MATTER_TEACH")
@IdClass(MatterTeachID.class)

public class MatterTeach implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5825144860286755834L;

	@Id
	private Long teacher_id;
	
	@Id
	private String matter_id;
	
	

	public MatterTeach() {}



	public Long getTeacher_id() {
		return teacher_id;
	}



	public String getMatter_id() {
		return matter_id;
	}



	public void setTeacher_id(Long teacher_id) {
		this.teacher_id = teacher_id;
	}



	public void setMatter_id(String matter_id) {
		this.matter_id = matter_id;
	}

}
