package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Attendance;
//import com.example.lms.Entity.Class;
import com.example.lms.Entity.ClassSchool;
import com.example.lms.Entity.Student;
import com.example.lms.Pojo.AttendancePojo;
import com.example.lms.Pojo.ClassPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.ClassRepo;
import com.example.lms.Service.ClassService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Service
@RequiredArgsConstructor

public class ClassImpl implements ClassService {
    private  final ClassRepo classRepo;
    public void addClass(ClassPojo classPojo) {
        ClassSchool classes = new ClassSchool();
        System.out.println("Attendance Date: " + classPojo.getClassName());
        classes.setClassName(classPojo.getClassName());
        classes.setFees(classPojo.getFees());
        classRepo.save(classes);
    }

    @Override
    public List<ClassSchool> getAll() {
        return classRepo.findAll();
    }

    @Override
    public Optional<ClassSchool> findById(String className) {
        return classRepo.findById(className);
    }

    @Override
    public void updateData(String className, ClassPojo classPojo) {
        Optional<ClassSchool> classOptional = classRepo.findById(className);
        if (classOptional.isPresent()) {
            ClassSchool existingClass = classOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingClass, classPojo);
            classRepo.save(existingClass); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + className + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(ClassSchool classes, ClassPojo classPojo) {
        System.out.println("Attendance Date: " + classPojo.getClassName());
        classes.setClassName(classPojo.getClassName());
        classes.setFees(classPojo.getFees());
        classRepo.save(classes);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(String classname) {
        return classRepo.existsById(classname);
    }
}
