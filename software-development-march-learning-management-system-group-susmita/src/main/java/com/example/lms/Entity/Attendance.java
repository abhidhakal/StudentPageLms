package com.example.lms.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity

@Getter
@Setter
@Table(name="Attendance")

public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="attendance_seq_gen")
    @SequenceGenerator(name="attendance_seq_gen",sequenceName="attendance_seq_gen",allocationSize=1)

    private Integer attendanceId;
    @ManyToOne
//   @JoinColumn(name="student_id" ,nullable=false )
    private Student studentId;
    @Column(name="Date", nullable=false)
    private LocalDate dateNow;
    @Column(name="Status", nullable=false)
    private boolean statusUpdate;
}
