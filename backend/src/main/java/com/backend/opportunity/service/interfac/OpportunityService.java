package com.backend.opportunity.service.interfac;

import com.backend.opportunity.dto.OpportunityDetails;
import com.backend.opportunity.dto.OpportunityList;
import com.backend.opportunity.dto.OpportunityRegister;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OpportunityService {

    OpportunityDetails addOpportunity(OpportunityRegister opportunityRegister, MultipartFile file, Long userId) throws Exception;

    OpportunityList getAllOpportunities(Long userId);

    OpportunityList getAllOpportunities();

    OpportunityDetails getOpportunity(Long opportunityId);

    List<String> getAllCities();

    OpportunityList getAllOpportunitiesByCity(String city);
}
