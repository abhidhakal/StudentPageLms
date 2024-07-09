package com.example.lms.Repo;

import com.example.lms.Entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo  extends JpaRepository<Subjects,Long> {
}
