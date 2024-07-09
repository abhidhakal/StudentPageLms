package com.example.lms.Controller;

import com.example.lms.Entity.Marks;
import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.MarksPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Service.MarksService;
import com.example.lms.Service.StudentService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/marks")
@RequiredArgsConstructor
@Getter


public class MarksController {
    private final MarksService marksService;



    @GetMapping("/get")
    public GlobalApiResponse<List<Marks>> getData() {
        return GlobalApiResponse.
                <List<Marks>>builder()
                .data(marksService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    @PostMapping("/save")
    public void save(@RequestBody MarksPojo marksPojo) {

        this.marksService.saveMarks(marksPojo);
    }

    @GetMapping("/get/{id}")
    public Optional<Marks> getData(@PathVariable Subjects id) {
        System.out.println("Hello");
        return marksService.findById(id.getSubId());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Subjects id) {
        this.marksService.deleteById(id.getSubId());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody MarksPojo marksPojo) {
        if (!marksService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        marksService.updateData(id, marksPojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }

}
