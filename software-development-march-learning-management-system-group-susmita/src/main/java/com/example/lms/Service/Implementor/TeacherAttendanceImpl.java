package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Student;
import com.example.lms.Entity.TeacherAttendance;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TeacherAttendancePojo;
import com.example.lms.Pojo.TeacherPojo;
import com.example.lms.Repo.TeacherAttendanceRepo;
import com.example.lms.Service.TeacherAttendanceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Getter
@Setter

@Service

public class TeacherAttendanceImpl implements TeacherAttendanceService {
    private final TeacherAttendanceRepo teacherAttendanceRepo;
    @Override
    public void takeTeacherAttendance(TeacherAttendancePojo teacherAttendancePojo) {
        TeacherAttendance teacherAttendance = new TeacherAttendance();
//        System.out.println("Attendance Date: " + teacherAttendancePojo.getDateNow());
        teacherAttendance.setId(teacherAttendancePojo.getId());
//        teacherAttendance.setIdentity(teacherAttendancePojo.getIdentity());
        // Use the date from attendancePojo instead of always using LocalDate.now()
        if (teacherAttendancePojo.getDateNow() != null) {
            teacherAttendance.setDateNow(teacherAttendancePojo.getDateNow());
        } else {
            teacherAttendance.setDateNow(LocalDate.now());
        }
        teacherAttendance.setStatusUpdate(teacherAttendancePojo.isStatusUpdate());
        System.out.println("Attendance before save: " + teacherAttendance);
        teacherAttendanceRepo.save(teacherAttendance);

    }

    @Override
    public List<TeacherAttendance> getAll() {
        return teacherAttendanceRepo.findAll();
    }

    @Override
    public Optional<TeacherAttendance> findById(Integer id) {
        return teacherAttendanceRepo.findById(id.longValue());
    }
    @Override
    public void updateData(Integer id, TeacherAttendancePojo teacherAttendancePojo) {
        Optional<TeacherAttendance> teacherAOptional = teacherAttendanceRepo.findById(id.longValue());
        if (teacherAOptional.isPresent()) {
            TeacherAttendance existingTeacher = teacherAOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingTeacher, teacherAttendancePojo);
            teacherAttendanceRepo.save(existingTeacher); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(TeacherAttendance teacherAttendance, TeacherAttendancePojo teacherAttendancePojo) {
        teacherAttendance.setId(teacherAttendancePojo.getId());
        teacherAttendance.setIdentity(teacherAttendancePojo.getIdentity());
        // Use the date from attendancePojo instead of always using LocalDate.now()
        if (teacherAttendancePojo.getDateNow() != null) {
            teacherAttendance.setDateNow(teacherAttendancePojo.getDateNow());
        } else {
            teacherAttendance.setDateNow(LocalDate.now());
        }
        teacherAttendance.setStatusUpdate(teacherAttendancePojo.isStatusUpdate());
        System.out.println("Attendance before save: " + teacherAttendance);
        teacherAttendanceRepo.save(teacherAttendance);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return teacherAttendanceRepo.existsById(id.longValue());
    }
}
