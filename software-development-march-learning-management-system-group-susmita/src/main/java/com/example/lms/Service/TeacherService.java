package com.example.lms.Service;


import com.example.lms.Entity.Teacher;

import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TeacherPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public interface TeacherService {
    void saveData(TeacherPojo teacherPojo);
    List<Teacher> getAll();
    void deleteById(Integer id);
    Optional<Teacher> findById(Integer id);
    void updateData(Integer id, TeacherPojo teacherPojo);
    boolean existsById(Integer id);
}
