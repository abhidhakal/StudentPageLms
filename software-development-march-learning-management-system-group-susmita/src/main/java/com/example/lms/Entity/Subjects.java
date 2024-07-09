package com.example.lms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subId;
    @Column(name="Subject_name" ,nullable = false)
    private String subjectName;

    @Column(name="credits")
    private String credit;

    @ManyToOne
//    @JoinColumn(name="class_name", nullable = false)
    private ClassSchool className;

    @ManyToMany

    private List<Student> studentId=new ArrayList<>();



}
