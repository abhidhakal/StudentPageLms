package com.example.lms.Service;

import com.example.lms.Entity.Payment;
import com.example.lms.Pojo.PaymentPojo;
import com.example.lms.Pojo.StudentPojo;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    void setPayment(PaymentPojo paymentPojo);
    List<Payment> getAll();
    Optional<Payment> findById(Long paymentId);
    void updateData(Integer id, PaymentPojo paymentPojo);
    boolean existsById(Integer id);


}
