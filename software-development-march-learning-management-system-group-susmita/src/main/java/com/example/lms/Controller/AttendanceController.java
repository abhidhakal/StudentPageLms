package com.example.lms.Controller;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Student;
import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Service.AttendanceService;
import com.example.lms.Service.StudentService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@Getter
@Setter

@RequestMapping("/attendance")

@RequiredArgsConstructor

public class AttendanceController {

    private  final AttendanceService attendanceService;
    @GetMapping("/get")
    public GlobalApiResponse<List<Attendance>> getData() {
        return GlobalApiResponse.
                <List<Attendance>>builder()
                .data(attendanceService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    //
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AttendancePojo attendance) {
        // Add validation or logging here if necessary
        if (attendance.getStudentId() == null) {
            // Handle the missing student_id case
            return ResponseEntity.badRequest().body("Student ID is required");
        }
        attendanceService.takeAttendance(attendance);
        return ResponseEntity.ok("Attendance saved successfully");
    }
    @GetMapping("/get/{id}")
    public Optional<Attendance> getData(@PathVariable Integer id) {
        System.out.println("Hello");
        return attendanceService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody AttendancePojo attendancePojo) {
        if (!attendanceService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        attendanceService.updateData(id, attendancePojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }

}
