package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Student;
import com.example.lms.Entity.Transportation;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.StudentRepo;
import com.example.lms.Service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Service


@RequiredArgsConstructor

public class StudentImpl implements StudentService {
    private final EntityManagerFactory emf; // Assuming EntityManagerFactory is available

        private final StudentRepo studentRepo;
//        @Override
//        public void saveData(StudentPojo studentPojo) {
//            if ( studentPojo.getStudentName() != null) {
//                try {
//                    Student student = new Student();
//                    System.out.println(studentPojo.getId());
//                    System.out.println(studentPojo.getStudentName());
//                    student.setId(studentPojo.getId());
//                    student.setStudentName(studentPojo.getStudentName());
//
//                    // Assuming other properties of StudentPojo need to be mapped as well
//
//                    studentRepo.save(student);
//                    System.out.println("Student saved successfully.");
//                } catch (Exception e) {
//                    System.err.println("Error occurred while saving student: " + e.getMessage());
//                    // You can log the exception or handle it based on your application's requirements
//                }
//            } else {
//                System.err.println("Cannot save student with null name.");
//                // You might want to throw an exception or handle this case differently based on your requirements
//            }
//        }
//@Override
//public void saveData(StudentPojo studentPojo) {
//    EntityManager em = emf.createEntityManager();
//    try {
//        em.getTransaction().begin();
//
//        Student student = new Student();
//        student.setStudentId(studentPojo.getStudentId().intValue());
//        student.setStudentName(studentPojo.getStudentName());
//
//        // Fetch Transportation entity if busNo is provided
//
//
//        // Set other student fields
//        student.setPassword(studentPojo.getPassword());
//        student.setEmail(studentPojo.getEmail());
//       student.setBusNo(studentPojo.getBusNo().intValue());
//        student.setContacts(studentPojo.getContacts());
//        student.setGrade(studentPojo.getEmail());
//        student.setSection(studentPojo.getEmail());
//        student.setRollNo(studentPojo.getRollNo());
//        student.setDateOfBirth(studentPojo.getDateOfBirth());
//        student.setAdmissionDate(studentPojo.getAdmissionDate());
//        student.setAge(studentPojo.getAge());
//
//        // Save student entity
//        studentRepo.save(student);
//
//        em.getTransaction().commit();
//        System.out.println("Student saved successfully.");
//    } catch (Exception e) {
//        em.getTransaction().rollback();
//        System.err.println("Error occurred while saving student: " + e.getMessage());
//    } finally {
//        em.close();
//    }
//}


    @Override
    public void saveData(StudentPojo studentPojo) {
        Student student=new Student();
        student.setBusNo(studentPojo.getBusNo());

        student.setStudentId(studentPojo.getStudentId().intValue());
        student.setStudentName(studentPojo.getStudentName());
        student.setPassword(studentPojo.getPassword());
        student.setEmail(studentPojo.getEmail());
        student.setContacts(studentPojo.getContacts());
//        student.setClassName(studentPojo.getClassName());

        student.setSection(studentPojo.getEmail());
        student.setRollNo(studentPojo.getRollNo());
        student.setDateOfBirth(studentPojo.getDateOfBirth());
        student.setAdmissionDate(studentPojo.getAdmissionDate());
        student.setAge(studentPojo.getAge());

        // Save student entity
        studentRepo.save(student);


    }

    @Override
        public List<Student> getAll() {
            System.out.println("My name is krishna");
            return studentRepo.findAll();
        }
        @Override
        public void deleteById(Long id) {
            System.out.println("My name is krishna bhandari");
            studentRepo.deleteById(id.intValue());
        }

    @Override
    public Optional<Student> findById(Long id) {
        System.out.println("My name is krishna bh");
        return studentRepo.findById(id.intValue());
        }

    @Override
    public void updateData(Integer id, StudentPojo studentPojo) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingStudent, studentPojo);
            studentRepo.save(existingStudent); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Student student, StudentPojo studentPojo) {
        student.setBusNo(studentPojo.getBusNo());
        student.setStudentName(studentPojo.getStudentName());
        student.setPassword(studentPojo.getPassword());
        student.setEmail(studentPojo.getEmail());
        student.setContacts(studentPojo.getContacts());
        student.setSection(studentPojo.getSection());
        student.setRollNo(studentPojo.getRollNo());
        student.setDateOfBirth(studentPojo.getDateOfBirth());
        student.setAdmissionDate(studentPojo.getAdmissionDate());
        student.setAge(studentPojo.getAge());
        studentRepo.save(student);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return studentRepo.existsById(id);
    }

}




