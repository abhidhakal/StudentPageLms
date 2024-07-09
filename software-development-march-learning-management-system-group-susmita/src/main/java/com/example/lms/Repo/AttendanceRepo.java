package com.example.lms.Repo;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {


}
