package com.example.lms.Service;

import com.example.lms.Entity.ClassSchool;
import com.example.lms.Pojo.ClassPojo;
import com.example.lms.Pojo.StudentPojo;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    void addClass(ClassPojo classPojo);
    List<ClassSchool> getAll();
    Optional<ClassSchool> findById(String className);
    void updateData(String className, ClassPojo classPojo);
    boolean existsById(String className);
}
