package com.example.lms.Pojo;

import com.example.lms.Entity.ClassSchool;
import com.example.lms.Entity.Subjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TeacherPojo {
    private Long id;
    private List<ClassSchool> classSchool;
    private List<Subjects> subjects;
    private String teacherName;
    private String password;
    private String email;
    private Integer contacts;
    private LocalDate dateOfBirth;
    private LocalDate joinDate;
    private Integer age;
}
