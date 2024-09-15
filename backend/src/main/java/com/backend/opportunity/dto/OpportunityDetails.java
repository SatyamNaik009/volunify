package com.backend.opportunity.dto;

import java.time.LocalDate;

public record OpportunityDetails(Long userId, Long opportunityId, String title, String description, String category, String location,String city, String skillsRequired, Integer volunteerCount,
                                 LocalDate startDate, LocalDate endDate,String photoUrl) {
}
