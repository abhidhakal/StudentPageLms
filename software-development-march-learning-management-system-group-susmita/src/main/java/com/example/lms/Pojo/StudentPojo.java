package com.example.lms.Pojo;


import com.example.lms.Entity.ClassSchool;
import com.example.lms.Entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentPojo {
    private Long studentId;
    private Transportation busNo;
    private String studentName;
    private String Password;
    private String Email;
    private Integer Contacts;
    private Integer RollNo;
  private ClassSchool grade;
    private String Section;
    private Date DateOfBirth;
    private Date AdmissionDate;
    private Integer Age;

}
