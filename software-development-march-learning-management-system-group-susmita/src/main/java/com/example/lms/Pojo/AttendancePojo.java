package com.example.lms.Pojo;

import com.example.lms.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

@AllArgsConstructor
@RequiredArgsConstructor

public class AttendancePojo {
    private Integer attendanceId;
    private Student studentId;
    private LocalDate dateNow;
    private boolean statusUpdate;
}
