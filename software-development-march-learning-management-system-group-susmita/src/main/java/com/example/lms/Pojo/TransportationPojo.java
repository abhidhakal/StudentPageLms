package com.example.lms.Pojo;

import com.example.lms.Entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class TransportationPojo {
    private Integer busNo;
//    private List<Student> studentId;
    private String route;
    private Integer busFee;
}
