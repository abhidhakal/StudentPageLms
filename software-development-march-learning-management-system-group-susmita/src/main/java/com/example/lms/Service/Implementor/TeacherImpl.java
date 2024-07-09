package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Teacher;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.TeacherRepo;
import com.example.lms.Pojo.TeacherPojo;
import com.example.lms.Service.TeacherService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Setter


public class TeacherImpl implements TeacherService {
    private final TeacherRepo teacherRepo;

    public void saveData(TeacherPojo teacherPojo) {
        if (teacherPojo.getTeacherName() == null) {
            System.out.println("teacherName is null");
        } else {
            System.out.println("teacherName: " + teacherPojo.getTeacherName());
        }

        Teacher teacher = new Teacher();
        teacher.setId(teacherPojo.getId());
        teacher.setClassSchool(teacherPojo.getClassSchool());
        teacher.setSubjects(teacherPojo.getSubjects());
        teacher.setTeacherName(teacherPojo.getTeacherName());
         teacher.setPassword(teacherPojo.getPassword());
        teacher.setEmail(teacherPojo.getEmail());
        teacher.setContacts(teacherPojo.getContacts());
        teacher.setDateOfBirth(teacherPojo.getDateOfBirth());
        teacher.setJoinDate(teacherPojo.getJoinDate());
        teacher.setAge(teacherPojo.getAge());
        teacherRepo.save(teacher);
    }


    @Override
    public List<Teacher> getAll() {
        System.out.println("My name is krishna");
        return teacherRepo.findAll();
    }



    public void deleteById(Integer id) {
        System.out.println("My name is krishna bhandari");
        teacherRepo.deleteById(Long.valueOf(id));
    }


    public Optional<Teacher> findById(Integer id) {
        System.out.println("My name is krishna bh");
        return teacherRepo.findById(id.longValue());
    }
    @Override
    public void updateData(Integer id, TeacherPojo teacherPojo) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(id.longValue());
        if (teacherOptional.isPresent()) {
            Teacher existingStudent = teacherOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingStudent, teacherPojo);
            teacherRepo.save(existingStudent); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Teacher teacher, TeacherPojo teacherPojo) {
        teacher.setId(teacherPojo.getId());
        teacher.setTeacherName(teacherPojo.getTeacherName());
        teacher.setClassSchool(teacherPojo.getClassSchool());
        teacher.setSubjects(teacherPojo.getSubjects());
        teacher.setPassword(teacherPojo.getPassword());
        teacher.setEmail(teacherPojo.getEmail());
        teacher.setContacts(teacherPojo.getContacts());
        teacher.setDateOfBirth(teacherPojo.getDateOfBirth());
        teacher.setJoinDate(teacherPojo.getJoinDate());
        teacher.setAge(teacherPojo.getAge());
        teacherRepo.save(teacher);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return teacherRepo.existsById(id.longValue());
    }
}
