package com.example.lms.Service.Implementor;

import com.example.lms.Entity.Notices;
import com.example.lms.Entity.Student;
import com.example.lms.Pojo.NoticesPojo;
import com.example.lms.Pojo.StudentPojo;
import com.example.lms.Repo.NoticeRepo;
import com.example.lms.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class NoticesImpl implements NoticeService {
    private final NoticeRepo noticeRepo;
    @Override
    public void publishNotice(NoticesPojo noticesPojo) {
        Notices notices=new Notices();
        notices.setNoticeId(noticesPojo.getNoticeId());
        notices.setTitle(noticesPojo.getTitle());
        notices.setDescription(noticesPojo.getDescription());
        notices.setUploadedDate( LocalDate.now());
//        if (noticesPojo.getUploadDate() != null) {
//            notices.setUploadedDate(noticesPojo.getUploadDate());
//        } else {
//            notices.setUploadedDate(LocalDate.now());
//        }


            noticeRepo.save(notices);


    }

    @Override
    public List<Notices> getAll() {
        return noticeRepo.findAll();
    }

    @Override
    public void deleteById(Integer noticeId) {
        noticeRepo.deleteById(noticeId.longValue());

    }

    @Override
    public Optional<Notices> findById(Integer noticeId) {
        return noticeRepo.findById(noticeId.longValue());
    }

    @Override
    public void updateData(Integer id, NoticesPojo noticesPojo) {
        Optional<Notices> noticesOptional = noticeRepo.findById(id.longValue());
        if (noticesOptional.isPresent()) {
            Notices existingNotices = noticesOptional.get();
            // Update the existing student with the data from studentPojo
            updateStudentProperties(existingNotices, noticesPojo);
            noticeRepo.save(existingNotices); // Save the updated student
        } else {
            // Handle the case where the student with the given ID does not exist
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    // Helper method to update properties of Student based on StudentPojo
    private void updateStudentProperties(Notices notices, NoticesPojo noticesPojo) {
        notices.setNoticeId(noticesPojo.getNoticeId());
        notices.setTitle(noticesPojo.getTitle());
        notices.setDescription(noticesPojo.getDescription());
        notices.setUploadedDate(LocalDate.now());


        noticeRepo.save(notices);

        // You may need to update other properties here
    }

    @Override
    public boolean existsById(Integer id) {
        return noticeRepo.existsById(id.longValue());
    }
}
