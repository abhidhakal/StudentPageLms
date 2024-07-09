package com.example.lms.Service;

import com.example.lms.Entity.Marks;
import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.MarksPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.MarksRepo;

import java.util.List;
import java.util.Optional;

public interface MarksService {

    void saveMarks(MarksPojo marksPojo);
    List<Marks> getAll();
    void deleteById(Integer id);
    Optional<Marks> findById(Integer id);

    void updateData(Integer id, MarksPojo marksPojo);
    boolean existsById(Integer id);
}
