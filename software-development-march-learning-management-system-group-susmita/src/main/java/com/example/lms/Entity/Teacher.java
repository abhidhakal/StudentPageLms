package com.example.lms.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@Entity
@Getter

@Setter
@Table
@RequiredArgsConstructor
public class Teacher {



        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="teachers_seq_gen")
        @SequenceGenerator(name="teachers_seq_gen",sequenceName="teachers_seq",allocationSize=1)
        @Id
        private Long id;
        @OneToMany
        private List<ClassSchool> classSchool;
        @OneToMany
        private List<Subjects> subjects;
        @Column(name="teacher_name" ,nullable=true, length=100)
        private String  teacherName;
        @Column(name="password",nullable = false,length=100)
        private String password;
        @Column(name="email",nullable = false,length=100)
        private String email;
        @Column(name="contacts",nullable = false,length=100)
        private Integer contacts;
        @Column(name="date_of_birth",nullable = false,length=100)
        private LocalDate dateOfBirth;
        @Column(name="join_date",nullable = false,length=100)
        private LocalDate joinDate;
        @Column(name="age",nullable = false,length=100)
        private Integer age;





    }


