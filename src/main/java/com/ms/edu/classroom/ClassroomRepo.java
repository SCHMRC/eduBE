package com.ms.edu.classroom;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClassroomRepo extends JpaRepository<Classroom, ClassroomID>{
	
	@Modifying
	@Query(value =  "INSERT INTO CLASS (YEAR,SECTION) VALUES(:year,:section)",nativeQuery = true)
    @Transactional
	void saveClassroom(@Param("year") String year, @Param("section") String section);
	

}
