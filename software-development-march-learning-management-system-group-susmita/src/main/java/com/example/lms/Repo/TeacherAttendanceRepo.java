package com.example.lms.Repo;

import com.example.lms.Entity.TeacherAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeacherAttendanceRepo extends JpaRepository<TeacherAttendance,Long> {
}
