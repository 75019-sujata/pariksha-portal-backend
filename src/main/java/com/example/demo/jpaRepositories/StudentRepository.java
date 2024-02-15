package com.example.demo.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.StudentModel;

import jakarta.transaction.Transactional;
@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long>{

	@Transactional
    @Modifying
	@Query("delete FROM StudentModel s WHERE s.student_prn = :studentPrn")
    void deleteByStudentPrn(@Param("studentPrn") String studentPrn);
	
	  @Transactional
      @Modifying
	  @Query("UPDATE StudentModel s SET s.student_name = :name, s.student_prn = :prn WHERE s.id = :id")
	  void updateStudent(@Param("prn") String studentPrn, @Param("name") String name, @Param("id") long id);
	  
	  @Query("SELECT s.id FROM StudentModel s WHERE s.student_prn = :studentPrn")
	    long getStudentByPrn(@Param("studentPrn") String studentPrn);  
}
	