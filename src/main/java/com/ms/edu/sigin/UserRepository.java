package com.ms.edu.sigin;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.edu.model.User;
import com.ms.edu.teacher.Teacher;


public interface UserRepository extends JpaRepository<User, String>{


	@Modifying
	@Query(value =  "INSERT INTO USER (email, password, role ) VALUES (:email, :password, :role)",nativeQuery = true)
    @Transactional
	void saveUser(@Param("email") String email, @Param("password") String password, @Param("role") String role);
	
	
	@Query(value =  "SELECT * FROM USER WHERE EMAIL = :email",nativeQuery = true)
    @Transactional
    User isExistUser(@Param("email") String email);

}
