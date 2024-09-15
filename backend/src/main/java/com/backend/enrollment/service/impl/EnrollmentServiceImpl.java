package com.backend.enrollment.service.impl;

import com.backend.enrollment.dto.EnrollmentDetails;
import com.backend.enrollment.entity.Enrollment;
import com.backend.enrollment.repository.EnrollmentRepository;
import com.backend.enrollment.service.intefac.EnrollmentService;
import com.backend.opportunity.entity.Opportunity;
import com.backend.opportunity.repository.OpportunityRepository;
import com.backend.user.entity.User;
import com.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean enrollUser(Long userId, Long opportunityId) {
        try {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setOpportunity(opportunity);
        enrollmentRepository.save(enrollment);
        sendEnrollmentEmail(user, opportunity);
        sendEnrollmentEmailToAdmin(opportunity.getUser(), user,opportunity);
        return true;
    } catch (IllegalArgumentException | DataAccessException e) {
        System.err.println("Enrollment failed: " + e.getMessage());
        return false;
    }
    }

    @Override
    public List<EnrollmentDetails> getUserEnroll(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);

        return enrollments.stream()
                .map(enrollment -> new EnrollmentDetails(
                        enrollment.getId(),
                        enrollment.getUser().getId(),
                        enrollment.getOpportunity().getId()
                ))
                .toList();
    }

    private void sendEnrollmentEmailToAdmin(User admin, User user,Opportunity opportunity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(admin.getEmail());
        message.setSubject("New Enrollment: " + opportunity.getTitle());
        message.setText("Dear " + admin.getName() + ",\n\n"
                + "New enrolling has been done in the opportunity: " + opportunity.getTitle() + ".\n"
                + "by:"+user.getName()+"\n\n"
                + "Regards,\nVolunteerHub Team");
        mailSender.send(message);
    }

    private void sendEnrollmentEmail(User user, Opportunity opportunity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Enrollment Confirmation: " + opportunity.getTitle());
        message.setText("Dear " + user.getName() + ",\n\n"
                + "Thank you for enrolling in the opportunity: " + opportunity.getTitle() + ".\n"
                + "We look forward to your participation.\n\n"
                + "Regards,\nVolunteerHub Team");

        mailSender.send(message);
    }
}
