package com.backend.opportunity.mapper;

import com.backend.opportunity.dto.OpportunityDetails;
import com.backend.opportunity.dto.OpportunityList;
import com.backend.opportunity.dto.OpportunityRegister;
import com.backend.opportunity.entity.Opportunity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OpportunityMapper {

    @Mapping(target = "user", ignore = true)
    Opportunity toOpportunity(OpportunityRegister opportunityRegister);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "id", target = "opportunityId")
    OpportunityDetails toOpportunityDetails(Opportunity opportunity);

    default OpportunityList toOpportunityList(List<OpportunityDetails> opportunityDetailsList) {
        return new OpportunityList(opportunityDetailsList);
    }
}

