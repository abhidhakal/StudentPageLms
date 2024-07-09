package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Transportation;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TransportationPojo;
import com.example.lms.Repo.TransportationRepo;
import com.example.lms.Service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service

public class TransportationImpl implements TransportationService {
    private final TransportationRepo transportationRepo;
    @Override
    public void addVehicle(TransportationPojo transportationPojo) {
        Transportation transport=new Transportation();
        transport.setBusNo(transportationPojo.getBusNo());

//        if (transportationPojo.getStudentId() != null) {
//            transport.setStudentId(transportationPojo.getStudentId());
//        } else {
//            System.out.println("error or null value");
//
//        }
        transport.setRoute(transportationPojo.getRoute());
        transport.setBusFee(transportationPojo.getBusFee());
        transportationRepo.save(transport);

    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.findAll();
    }
    @Override
    public void deleteById(Integer busId) {
        System.out.println("My name is krishna bhandari");
        transportationRepo.deleteById(busId.longValue());
    }

    @Override
    public Optional<Transportation> findById(Integer busId) {
        return transportationRepo.findById(busId.longValue());
    }
    @Override
    public void updateData(Integer id, TransportationPojo transportationPojo) {
        Optional<Transportation> transportOptional = transportationRepo.findById(id.longValue());
        if (transportOptional.isPresent()) {
            Transportation existingTransport = transportOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingTransport, transportationPojo);
            transportationRepo.save(existingTransport); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Transportation transport, TransportationPojo transportationPojo) {
        transport.setRoute(transportationPojo.getRoute());
        transport.setBusNo(transportationPojo.getBusNo());
        transport.setBusFee(transportationPojo.getBusFee());
        transportationRepo.save(transport);
        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return transportationRepo.existsById(id.longValue());
    }
}
