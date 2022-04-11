package com.ms.edu.relationshipRepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassTeachRepository extends JpaRepository<ClassTeach, ClassTeachID>{
	
	
	
	@Modifying
	@Query(value = "INSERT INTO CLASS_TEACH (teacher_id, year_id, section_id) VALUES (:teacherId,:yearId,:sectionId)", nativeQuery = true)
	@Transactional
	void saveClassTeachRelation(@Param("teacherId") Long teacher_id,@Param("yearId") String year_id,@Param("sectionId") String section_id);
	
	@Query(value = "SELECT * FROM CLASS_TEACH WHERE teacher_id = :teacherId AND year_id = :yearId AND section_id = :sectionId", nativeQuery = true)
	@Transactional
	ClassTeach selectClassTeachRelation(@Param("teacherId") Long teacher_id,@Param("yearId") String year_id,@Param("sectionId") String section_id);
	
	@Modifying
	@Query(value = "DELETE FROM CLASS_TEACH WHERE YEAR_ID = :yearId AND SECTION_ID = :sectionId", nativeQuery = true)
	@Transactional
	void removeClassTeachRelation(@Param("yearId") String year_id,@Param("sectionId") String section_id);
}
