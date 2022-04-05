package com.ms.edu.sigin;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.edu.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>{
	
	@Modifying
	@Query(value =  "INSERT INTO PROFILE (user_id , name, phone, surname ) VALUES (:user_id, :name, :phone, :surname)",nativeQuery = true)
    @Transactional
	void saveProfile(@Param("user_id") String user_id, @Param("name") String name, @Param("phone") String phone, @Param("surname") String surname);

}
