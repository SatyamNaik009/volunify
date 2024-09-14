package com.backend.enrollment.dto;

import java.sql.Timestamp;

public record EnrollmentDetails(Long enrollmentId, Long userId, Long opportunityId, Timestamp timestamp) {
}
