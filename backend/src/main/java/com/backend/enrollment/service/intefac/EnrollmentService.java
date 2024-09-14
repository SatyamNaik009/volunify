package com.backend.enrollment.service.intefac;

import com.backend.enrollment.dto.EnrollmentDetails;
import com.backend.enrollment.entity.Enrollment;

public interface EnrollmentService {
    Boolean enrollUser(Long userId, Long opportunityId);
}
