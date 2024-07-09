package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Subjects;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Pojo.SubjectsPojo;
import com.example.lms.Repo.SubjectRepo;
import com.example.lms.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Primary

public class SubjectsImpl implements SubjectService {
    private final SubjectRepo subjectRepo;
    @Override
    public void addSubject(SubjectsPojo subjectsPojo) {
        Subjects subject=new Subjects();
        subject.setSubId(subjectsPojo.getSubId());
        subject.setSubjectName(subjectsPojo.getSubjectName());
        subject.setCredit(subjectsPojo.getCredits());
        subject.setClassName(subjectsPojo.getClassName());
        subjectRepo.save(subject);

    }

    @Override
    public List<Subjects> getAll() {
        return subjectRepo.findAll();
    }

    @Override
    public void deleteById(Integer subId) {
        subjectRepo.deleteById(subId.longValue());

    }

    @Override
    public Optional<Subjects> findById(Integer subId) {
        return subjectRepo.findById(subId.longValue());
    }

    @Override
    public void updateData(Integer id, SubjectsPojo subjectsPojo) {
        Optional<Subjects> subjectOptional = subjectRepo.findById(id.longValue());
        if (subjectOptional.isPresent()) {
            Subjects existingSubject = subjectOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingSubject, subjectsPojo);
            subjectRepo.save(existingSubject); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Subjects subject, SubjectsPojo subjectsPojo) {

        subject.setSubId(subjectsPojo.getSubId());
        subject.setSubjectName(subjectsPojo.getSubjectName());
        subject.setCredit(subjectsPojo.getCredits());
        subject.setClassName(subjectsPojo.getClassName());
        subjectRepo.save(subject);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return subjectRepo.existsById(id.longValue());
    }
}
