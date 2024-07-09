package com.example.lms.Pojo;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Teacher;
import com.example.lms.Entity.TeacherAttendance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class TeacherAttendancePojo {
    private Integer identity;
    private Teacher id;
    private LocalDate dateNow;
    private boolean statusUpdate;

}
