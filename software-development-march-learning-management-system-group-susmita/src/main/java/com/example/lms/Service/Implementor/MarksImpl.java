package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Marks;
import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.MarksPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.MarksRepo;
import com.example.lms.Service.MarksService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Getter
@RequiredArgsConstructor

public class MarksImpl implements MarksService {
    private final MarksRepo marksRepo;
    @Override
    public void saveMarks(MarksPojo marksPojo) {
        Marks marks=new Marks();

        marks.setSubId(marksPojo.getSubId());
        marks.setStudent(marksPojo.getStudentId());
        marks.setFm(marksPojo.getFm());
        marks.setPm(marksPojo.getPm());
        marks.setOm(marksPojo.getOm());
        marks.setMarksid(marksPojo.getMarksId());
        marksRepo.save(marks);

    }

    @Override
    public List<Marks> getAll() {
        return marksRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        marksRepo.deleteById(id.longValue());

    }

    @Override
    public Optional<Marks> findById(Integer id) {
        return marksRepo.findById(id.longValue());
    }

    @Override
    public void updateData(Integer id, MarksPojo marksPojo) {
        Optional<Marks> marksOptional = marksRepo.findById(id.longValue());
        if (marksOptional.isPresent()) {
            Marks existingStudent = marksOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingStudent, marksPojo);
            marksRepo.save(existingStudent); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Marks marks, MarksPojo marksPojo) {

        marks.setSubId(marksPojo.getSubId());
        marks.setFm(marksPojo.getFm());
        marks.setPm(marksPojo.getPm());
        marks.setOm(marksPojo.getOm());
        marks.setStudent(marksPojo.getStudentId());
        marks.setMarksid(marksPojo.getMarksId());
        marksRepo.save(marks);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return marksRepo.existsById(id.longValue());
    }
}
