package com.example.lms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity

public class TeacherAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="teacherattendance_seq_gen")
    @SequenceGenerator(name="teacherattendance_seq_gen",sequenceName="teacherattendance_seq_gen",allocationSize=1)

    private Integer identity;
    @ManyToOne
//    @JoinColumn(name="teacher_id" ,nullable=false )
    private Teacher id;
    @Column(name="Date", nullable=false)
    private LocalDate dateNow;
    @Column(name="Status", nullable=false)
    private boolean statusUpdate;
}
