package com.example.lms.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor

public class Marks {
    @Id
    private  Integer marksid;
    @ManyToOne

    @JoinColumn(name="subId")

    private Subjects subId;
    @ManyToOne
    private Student student;


    @Column(name="Fm", nullable = false)
    private Integer fm;
    @Column(name="Pm", nullable = false)
    private Integer pm;

    @Column(name="Om", nullable = false)
    private Integer om;






}
