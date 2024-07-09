package com.example.lms.Service;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Teacher;
import com.example.lms.Entity.TeacherAttendance;
import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TeacherAttendancePojo;

import java.util.List;
import java.util.Optional;

public interface TeacherAttendanceService {
    void takeTeacherAttendance(TeacherAttendancePojo teacherAttendancePojo);
    List<TeacherAttendance> getAll();
    Optional<TeacherAttendance> findById(Integer identity);
    void updateData(Integer id, TeacherAttendancePojo teacherAttendancePojo);
    boolean existsById(Integer id);
}
