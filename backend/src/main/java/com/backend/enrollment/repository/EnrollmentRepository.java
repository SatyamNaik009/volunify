package com.backend.enrollment.repository;

import com.backend.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByOpportunityId(Long opportunityId);
}
