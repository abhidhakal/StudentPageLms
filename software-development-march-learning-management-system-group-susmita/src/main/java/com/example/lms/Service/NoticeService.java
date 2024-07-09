package com.example.lms.Service;

import com.example.lms.Entity.Notices;
import com.example.lms.Entity.Student;
import com.example.lms.Pojo.NoticesPojo;
import com.example.lms.Pojo.StudentPojo;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    void publishNotice(NoticesPojo noticesPojo);
    List<Notices> getAll();
    void deleteById(Integer noticeId);
    Optional<Notices> findById(Integer noticeId);
    void updateData(Integer id, NoticesPojo noticesPojo);
    boolean existsById(Integer id);
}
