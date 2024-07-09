package com.example.lms.Repo;

import com.example.lms.Entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MarksRepo extends JpaRepository<Marks,Long> {

    @Query("SELECT m FROM Marks m " +
            "JOIN FETCH m.student s " +
            "JOIN FETCH m.subId sub " +
            "WHERE s.studentId = :studentId")
    List<Marks> findByStudentIdWithSubjects(@Param("studentId") Integer studentId);
}
