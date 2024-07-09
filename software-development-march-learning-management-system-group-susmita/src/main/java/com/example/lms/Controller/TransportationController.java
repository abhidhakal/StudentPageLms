package com.example.lms.Controller;

import com.example.lms.Entity.Transportation;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TransportationPojo;
import com.example.lms.Service.TransportationService;
import com.example.lms.Shared.GlobalApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/transport")
public class TransportationController {
    private final TransportationService transportationService;
    @GetMapping("/get")
    public GlobalApiResponse<List<Transportation>> getData() {
        return GlobalApiResponse.
                <List<Transportation>>builder()
                .data(transportationService.getAll())
                .statusCode(200)
                .message("Data retrieved successfully!")
                .build();
    }

    //
    @PostMapping("/save")
    public void save(@RequestBody TransportationPojo transportationPojo) {

        this.transportationService.addVehicle(transportationPojo);
    }

    @GetMapping("/get/{id}")
    public Optional<Transportation> getData(@PathVariable Integer id) {
        System.out.println("Hello");
        return transportationService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.transportationService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody TransportationPojo transportationPojo) {
        if (!transportationService.existsById(id)) {
            return new ResponseEntity<>("Students id" + id + " not found", HttpStatus.NOT_FOUND);
        }

        // Update the existing ground with the provided ID
        transportationService.updateData(id, transportationPojo);

        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }

}
