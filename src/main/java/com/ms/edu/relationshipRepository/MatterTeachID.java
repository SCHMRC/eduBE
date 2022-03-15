package com.ms.edu.relationshipRepository;

import java.io.Serializable;
import java.util.Objects;

public class MatterTeachID implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4663695339499405009L;
	private Long teacher_id;
	private String matter_id;

	public MatterTeachID(Long teacherID, String matterID) {
		super();
		this.teacher_id = teacherID;
		this.matter_id = matterID;
	}

	public MatterTeachID() {}

	public Long getTeacherID() {
		return teacher_id;
	}

	public String getMatterID() {
		return matter_id;
	}

	public void setTeacherID(Long teacherID) {
		this.teacher_id = teacherID;
	}

	public void setMatterID(String matterID) {
		this.matter_id = matterID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matter_id, teacher_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatterTeachID other = (MatterTeachID) obj;
		return Objects.equals(matter_id, other.matter_id) && Objects.equals(teacher_id, other.teacher_id);
	}

}
