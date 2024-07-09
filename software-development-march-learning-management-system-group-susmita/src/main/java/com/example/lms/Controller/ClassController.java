package com.example.lms.Controller;

import com.example.lms.Entity.ClassSchool;
import com.example.lms.Entity.Payment;
import com.example.lms.Pojo.ClassPojo;
import com.example.lms.Pojo.PaymentPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Service.ClassService;
import com.example.lms.Service.PaymentService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
@Setter

public class ClassController {
    private  final ClassService classService;
    @GetMapping("/get")
    public GlobalApiResponse<List<ClassSchool>> getData() {
        return GlobalApiResponse.
                <List<ClassSchool>>builder()
                .data(classService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    //
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClassPojo classPojo) {
        // Add validation or logging here if necessary
        if (classPojo.getClassName() == null) {
            // Handle the missing student_id case
            return ResponseEntity.badRequest().body("Class Name is required");
        }
        classService.addClass(classPojo);
        return ResponseEntity.ok("Payment saved successfully");
    }
    @GetMapping("/get/{id}")
    public Optional<ClassSchool> getData(@PathVariable String id) {
        System.out.println("Hello");
        return classService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable String className, @RequestBody ClassPojo classPojo) {
        if (!classService.existsById(className)) {
            return new ResponseEntity<>("Students id" + className + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        classService.updateData(className, classPojo);

        return ResponseEntity.ok("Student with ID " + className + " updated successfully");
    }
}
