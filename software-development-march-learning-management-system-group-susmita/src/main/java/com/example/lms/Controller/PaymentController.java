package com.example.lms.Controller;


import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Payment;
import com.example.lms.Entity.Student;

import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.PaymentPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Service.AttendanceService;
import com.example.lms.Service.PaymentService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@RestController

@RequiredArgsConstructor
@RequestMapping("/payment")


public class PaymentController {

    private  final PaymentService paymentService;
    @GetMapping("/get")
    public GlobalApiResponse<List<Payment>> getData() {
        return GlobalApiResponse.
                <List<Payment>>builder()
                .data(paymentService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    //
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentPojo paymentPojo) {
        // Add validation or logging here if necessary
        if (paymentPojo.getStudentId() == null) {
            // Handle the missing student_id case
            return ResponseEntity.badRequest().body("Student ID is required");
        }
        paymentService.setPayment(paymentPojo);
        return ResponseEntity.ok("Payment saved successfully");
    }
    @GetMapping("/get/{id}")
    public Optional<Payment> getData(@PathVariable Long id) {
        System.out.println("Hello");
        return paymentService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody PaymentPojo paymentPojo) {
        if (!paymentService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        paymentService.updateData(id, paymentPojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }


}
