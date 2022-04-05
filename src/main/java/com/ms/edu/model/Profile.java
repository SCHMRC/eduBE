package com.ms.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PROFILE")

public class Profile {
	
	@Id
	@Column(name = "user_id")
	private String id;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "name")
	private String name;
		
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "phone")
	private String phone;
	

	public Profile() {
		// TODO Auto-generated constructor stub
	}


	public Profile(String emailFk, String name, String surname, String phone) {
		super();
		this.id = emailFk;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
	}

}
