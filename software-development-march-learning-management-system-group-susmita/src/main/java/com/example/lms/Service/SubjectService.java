package com.example.lms.Service;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.SubjectsPojo;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    void addSubject(SubjectsPojo subjectsPojo);
    List<Subjects> getAll();
    void deleteById(Integer id);
    Optional<Subjects> findById(Integer id);
    void updateData(Integer id, SubjectsPojo subjectsPojo);
    boolean existsById(Integer id);
}
