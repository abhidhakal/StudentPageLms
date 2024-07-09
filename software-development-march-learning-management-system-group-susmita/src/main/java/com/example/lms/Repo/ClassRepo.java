package com.example.lms.Repo;

import com.example.lms.Entity.ClassSchool;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClassRepo extends JpaRepository<ClassSchool,String> {
}
