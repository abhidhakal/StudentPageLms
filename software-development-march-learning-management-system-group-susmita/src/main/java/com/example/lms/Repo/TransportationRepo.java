package com.example.lms.Repo;

import com.example.lms.Entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransportationRepo extends JpaRepository<Transportation,Long> {
}
