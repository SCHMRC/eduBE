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

	@Modifying
	@Query(value =  "INSERT INTO MATTER_TEACH ( MATTER_ID, TEACHER_ID) VALUES(:matter, :id_teacher)",nativeQuery = true)
    @Transactional
	Integer saveAddMatter(@Param("matter") String matter, @Param("id_teacher") long idTeacher);
	
	
	
	@Modifying
	@Query(value =  "DELETE FROM MATTER_TEACH WHERE MATTER_ID = :matter AND TEACHER_ID = :id_teacher",nativeQuery = true)
    @Transactional
    Integer removeMatterTech(@Param("matter") String matter, @Param("id_teacher") Long idTeacher);
	

}
