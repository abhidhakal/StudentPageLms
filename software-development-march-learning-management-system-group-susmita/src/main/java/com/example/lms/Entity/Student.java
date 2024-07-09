package com.example.lms.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity


@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Setter

@Table(name="students")
public class Student{
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="students_seq_gen")
    @SequenceGenerator(name="students_seq_gen",sequenceName="students_seq",allocationSize=1)
    @Id
    private Integer studentId;

//    @OneToMany(mappedBy = "student")
//    private List<Marks> marks = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "transportation_id", nullable = true)
    private Transportation busNo;

    @Column(name="student_name" ,nullable=true, length=100)
    private String  studentName;
    @Column(name="password",nullable = false,length=100)
    private String Password;
    @Column(name="email",nullable = false,length=100)
    private String Email;
    @Column(name="contacts",nullable = false,length=100)
    private Integer Contacts;

@ManyToOne
@JoinColumn(name="grade")
private ClassSchool grade;
    @Column(name="section",nullable = false,length=100)
    private String Section;
    @Column(name="roll_no",nullable = false,length=100)
    private Integer rollNo;
    @Column(name="date_of_birth",nullable = false,length=100)
    private Date DateOfBirth;
    @Column(name="admission_date",nullable = false,length=100)
    private Date AdmissionDate;
    @Column(name="age",nullable = false,length=100)
    private Integer Age;




}
