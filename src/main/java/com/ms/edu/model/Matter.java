package com.ms.edu.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ms.edu.teacher.Teacher;
import com.sun.istack.Nullable;

import lombok.Data;

@Entity
@Data
@Table(name="MATTER")
@SQLInsert(sql = "INSERT IGNORE INTO MATTER ( MATTER ) VALUES (?)")

public class Matter implements Serializable{
	
	private static final long serialVersionUID = -6606064536473556354L;
	

	@Id
	@Column(name = "matter")
	String matter;
	
	@ManyToMany(mappedBy = "matters")
	@Nullable
	List<Teacher> teachers = new ArrayList<>();
	
	public Matter(){}

	public Matter(String matter) {
		super();
		this.matter = matter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getMatter() {
		return matter;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}


	public void setMatter(String matter) {
		this.matter = matter;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matter other = (Matter) obj;
		return Objects.equals(matter, other.matter);
	};
	

}
