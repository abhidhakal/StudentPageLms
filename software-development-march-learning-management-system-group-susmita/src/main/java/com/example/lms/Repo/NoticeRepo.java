package com.example.lms.Repo;

import com.example.lms.Entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepo extends JpaRepository<Notices,Long> {
}
