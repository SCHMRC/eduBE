package com.ms.edu.classroom;

import java.io.Serializable;
import java.util.Objects;

public class ClassroomID implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1023108873806195244L;
	private String a_year;
	private String b_section;
	

	public ClassroomID() {
	}
	
	public ClassroomID(String year, String section) {
        this.a_year = year;
        this.b_section = section;
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

	@Override
	public int hashCode() {
		return Objects.hash(b_section, a_year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassroomID other = (ClassroomID) obj;
		return Objects.equals(b_section, other.b_section) && Objects.equals(a_year, other.a_year);
	}


}
