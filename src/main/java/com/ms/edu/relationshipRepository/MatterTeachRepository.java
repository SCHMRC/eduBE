package com.ms.edu.relationshipRepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.edu.teacher.Teacher;

public interface MatterTeachRepository extends JpaRepository<MatterTeach, MatterTeachID> {
	
	@Modifying
	@Query(value =  "INSERT INTO MATTER_TEACH (TEACHER_ID, MATTER_ID  ) VALUES (:teacherId, :matterId)",nativeQuery = true)
    @Transactional
    void saveRelationshipTM(@Param("teacherId") Long teacherId, @Param("matterId") String matterId);
	
	
	@Query(value =  "SELECT teacher_id , matter_id FROM MATTER_TEACH WHERE teacher_id = :teacherId AND matter_id = :matterId",nativeQuery = true)
    @Transactional
    MatterTeach isInRelationshipTM(@Param("teacherId") Long teacherId, @Param("matterId") String matterId);
}
