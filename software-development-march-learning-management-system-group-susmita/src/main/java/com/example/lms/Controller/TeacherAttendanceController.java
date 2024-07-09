package com.example.lms.Controller;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.TeacherAttendance;
import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TeacherAttendancePojo;
import com.example.lms.Service.AttendanceService;
import com.example.lms.Service.TeacherAttendanceService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/Teacher_attendance")

public class TeacherAttendanceController {

    private  final TeacherAttendanceService teacherAttendanceService;
    @GetMapping("/get")
    public GlobalApiResponse<List<TeacherAttendance>> getData() {
        return GlobalApiResponse.
                <List<TeacherAttendance>>builder()
                .data(teacherAttendanceService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    //
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TeacherAttendancePojo teacherAttendancePojo) {
        // Add validation or logging here if necessary
        if (teacherAttendancePojo.getIdentity() == null) {
            // Handle the missing student_id case
            return ResponseEntity.badRequest().body("Student ID is required");
        }
        teacherAttendanceService.takeTeacherAttendance(teacherAttendancePojo);
        return ResponseEntity.ok("Attendance saved successfully");
    }
    @GetMapping("/get/{id}")
    public Optional<TeacherAttendance> getData(@PathVariable Integer id) {
        System.out.println("Hello");
        return teacherAttendanceService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody TeacherAttendancePojo teacherAttendancePojo) {
        if (!teacherAttendanceService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        teacherAttendanceService.updateData(id, teacherAttendancePojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }
}
