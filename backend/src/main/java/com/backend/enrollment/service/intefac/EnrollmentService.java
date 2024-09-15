package com.backend.enrollment.service.intefac;

import com.backend.enrollment.dto.EnrollmentDetails;
import com.backend.enrollment.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Boolean enrollUser(Long userId, Long opportunityId);

    List<EnrollmentDetails> getUserEnroll(Long userId);
}
