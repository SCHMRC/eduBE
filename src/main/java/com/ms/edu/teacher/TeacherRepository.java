package com.ms.edu.teacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface TeacherRepository extends JpaRepository<Teacher, Long>  {
	
	List<Teacher> findByName(String name);
	
	@Modifying
	@Query(value =  "INSERT INTO TEACHER ( NAME , SURNAME, ADRESS, EDUMAIL, EMAIL, PHONE ) VALUES (:name, :surname, :adress, :edumail, :email, :phone)",nativeQuery = true)
    @Transactional
	void saveTeacher(@Param("name") String name, @Param("surname") String surname,@Param("adress") String adress, @Param("edumail") String edumail,@Param("email") String email, @Param("phone") String phone);
	
	
	@Query(value =  "SELECT * FROM TEACHER WHERE name = :name AND surname = :surname",nativeQuery = true)
    @Transactional
    Teacher isExistTeacher(@Param("name") String name, @Param("surname") String surname);
	
	@Query(value =  "SELECT * FROM TEACHER",nativeQuery = true)
    @Transactional
    List<ArrayList<Object>> getAllTeachers();
	
	@Query(value =  "SELECT T.ID,  T.NAME, T.SURNAME, T.ADRESS, T.EDUMAIL, T.EMAIL, T.PHONE, M.MATTER, CT.YEAR_ID, CT.SECTION_ID "
			+ "FROM TEACHER AS T , MATTER AS M "
			+ "INNER JOIN MATTER_TEACH AS MT ON MT.TEACHER_ID = T.ID "
			+ "INNER JOIN CLASS_TEACH AS CT ON CT.TEACHER_ID = T.ID "
			+ "AND MT.MATTER_ID = M.MATTER "
			+ "WHERE T.ID = :id",nativeQuery = true)
    @Transactional
    List<ArrayList<Object>>  getInfoTeacher(@Param("id") Long id);
	
	
	@Query(value =  "SELECT T.ID,  T.NAME, T.SURNAME, T.ADRESS, T.EDUMAIL, T.EMAIL, T.PHONE, M.MATTER "
			+ "FROM TEACHER AS T , MATTER AS M "
			+ "INNER JOIN MATTER_TEACH AS MT ON MT.TEACHER_ID = T.ID "
			+ "AND MT.MATTER_ID = M.MATTER "
			+ "WHERE T.ID = :id",nativeQuery = true)
    @Transactional
    List<ArrayList<Object>>  getInfoTeacherNullClass(@Param("id") Long id);
	
	@Query(value =  "SELECT T.ID,  T.NAME, T.SURNAME, M.MATTER, CT.YEAR_ID, CT.SECTION_ID "
			+ "FROM TEACHER AS T , MATTER AS M "
			+ "INNER JOIN MATTER_TEACH AS MT ON MT.TEACHER_ID = T.ID "
			+ "INNER JOIN CLASS_TEACH AS CT ON CT.TEACHER_ID = T.ID "
			+ "AND MT.MATTER_ID = M.MATTER "
			+ "ORDER BY T.SURNAME",nativeQuery = true)
    @Transactional
    List<ArrayList<Object>> getAllInfoTeacher();
	
	@Modifying
	@Query(value =  "DELETE FROM CLASS_TEACH WHERE TEACHER_ID = :id",nativeQuery = true)
    @Transactional
	Integer deleteRelTeacherClass(@Param("id") Long id);
	
	@Modifying
	@Query(value =  "DELETE FROM MATTER_TEACH WHERE TEACHER_ID = :id",nativeQuery = true)
    @Transactional
    Integer deleteRelTeacherMatter(@Param("id") Long id);
	
	
}
