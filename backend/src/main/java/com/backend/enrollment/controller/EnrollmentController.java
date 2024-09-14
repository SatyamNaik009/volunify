package com.backend.enrollment.controller;

import com.backend.enrollment.dto.EnrollmentDetails;
import com.backend.enrollment.entity.Enrollment;
import com.backend.enrollment.service.intefac.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/user/{userId}/enroll/{opportunityId}")
    public ResponseEntity<Boolean> enrollUser(@PathVariable Long userId, @PathVariable Long opportunityId) {

        Boolean flag = enrollmentService.enrollUser(userId, opportunityId);
        return ResponseEntity.status(HttpStatus.CREATED).body(flag);

    }
}
