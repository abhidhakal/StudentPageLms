package com.example.lms.Controller;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.SubjectsPojo;
import com.example.lms.Service.StudentService;
import com.example.lms.Service.SubjectService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")

public class SubjectController {
    private final SubjectService subjectService;



    @GetMapping("/get")
    public GlobalApiResponse<List<Subjects>> getData() {
        return GlobalApiResponse.
                <List<Subjects>>builder()
                .data(subjectService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    @PostMapping("/save")
    public void save(@RequestBody SubjectsPojo subjectsPojo) {

        this.subjectService.addSubject(subjectsPojo);
    }

    @GetMapping("/get/{id}")
    public Optional<Subjects> getData(@PathVariable Integer id) {
        System.out.println("Hello");
        return subjectService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.subjectService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody SubjectsPojo subjectsPojo) {
        if (!subjectService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        subjectService.updateData(id, subjectsPojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }

}
