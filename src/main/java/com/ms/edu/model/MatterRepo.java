package com.ms.edu.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatterRepo extends JpaRepository<Matter, String>{
	
	
	@Modifying
	@Query(value =  "INSERT INTO MATTER ( MATTER ) VALUES (:matter)",nativeQuery = true)
    @Transactional
	void saveMatter(@Param("matter") String matter);
	

}
