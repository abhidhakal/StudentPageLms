package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Payment;
import com.example.lms.Entity.Student;
import com.example.lms.Pojo.PaymentPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.PaymentRepo;
import com.example.lms.Service.PaymentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Setter
@Getter
@RequiredArgsConstructor
@Service

public class PaymentImpl implements PaymentService {
    private final PaymentRepo paymentRepo;


    @Override
    public void setPayment(PaymentPojo paymentPojo) {
        Payment payment = new Payment();
        System.out.println("Attendance Date: " + paymentPojo.getDate());
        payment.setPaymentId(paymentPojo.getPaymentId());
        payment.setStudentId(paymentPojo.getStudentId());
        payment.setTotal(paymentPojo.getTotal());
        payment.setPaid(paymentPojo.getPaid());
        payment.setDue(paymentPojo.getDue());
        // Use the date from attendancePojo instead of always using LocalDate.now()
        if (paymentPojo.getDate() != null) {
            payment.setDate(paymentPojo.getDate());
        } else {
            payment.setDate(LocalDate.now());
        }
        payment.setStatus(paymentPojo.isStatus());
        System.out.println("Attendance before save: " + payment);
        paymentRepo.save(payment);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Optional<Payment> findById(Long paymentId) {
        return paymentRepo.findById(paymentId);
    }

    @Override
    public void updateData(Integer id, PaymentPojo paymentPojo) {
        Optional<Payment> paymentOptional = paymentRepo.findById(id.longValue());
        if (paymentOptional.isPresent()) {
            Payment existingPayment = paymentOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingPayment, paymentPojo);
            paymentRepo.save(existingPayment); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Payment payment, PaymentPojo paymentPojo) {
        payment.setPaymentId(paymentPojo.getPaymentId());
        payment.setStudentId(paymentPojo.getStudentId());
        payment.setTotal(paymentPojo.getTotal());
        payment.setPaid(paymentPojo.getPaid());
        payment.setDue(paymentPojo.getDue());
        // Use the date from attendancePojo instead of always using LocalDate.now()
        if (paymentPojo.getDate() != null) {
            payment.setDate(paymentPojo.getDate());
        } else {
            payment.setDate(LocalDate.now());
        }
        payment.setStatus(paymentPojo.isStatus());
        System.out.println("Attendance before save: " + payment);
        paymentRepo.save(payment);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return paymentRepo.existsById(id.longValue());
    }
}
