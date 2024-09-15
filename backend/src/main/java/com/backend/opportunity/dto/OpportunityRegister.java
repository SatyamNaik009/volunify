package com.backend.opportunity.dto;

import java.time.LocalDate;

public record OpportunityRegister(String title, String description, String category, String location,String city, String skillsRequired, Integer volunteerCount,
                                  LocalDate startDate,LocalDate endDate) {
}
