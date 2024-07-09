package com.example.lms.Service;

import com.example.lms.Entity.Attendance;
import com.example.lms.Entity.Transportation;
import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.TransportationPojo;

import java.util.List;
import java.util.Optional;

public interface TransportationService {

    void addVehicle(TransportationPojo transportationPojo);
    List<Transportation> getAll();
    Optional<Transportation> findById(Integer busId);
    void deleteById(Integer busId);
    void updateData(Integer id, TransportationPojo transportationPojo);
    boolean existsById(Integer id);
}
