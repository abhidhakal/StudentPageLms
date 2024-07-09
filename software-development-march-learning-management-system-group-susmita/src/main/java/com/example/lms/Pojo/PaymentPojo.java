package com.example.lms.Pojo;

import com.example.lms.Entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class PaymentPojo {
    private Long paymentId;
    private LocalDate date;
    private Long total;
    private Long paid;
    private Long due;
    private boolean status;
    private Student studentId;
}
