package com.example.lms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Classes")
@Getter
@Setter

public class ClassSchool {
    @Id
    @Column(name="ClassName")
    private String className;

    @Column(name="Fees")
    private Long fees;

    @OneToMany
    private List<Subjects> subId=new ArrayList<>();

//    @OneToMany
//    private List<Student> studentId=new ArrayList<>();

}
