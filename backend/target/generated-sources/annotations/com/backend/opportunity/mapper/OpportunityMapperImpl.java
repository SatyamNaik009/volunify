package com.backend.opportunity.mapper;

import com.backend.opportunity.dto.OpportunityDetails;
import com.backend.opportunity.dto.OpportunityRegister;
import com.backend.opportunity.entity.Opportunity;
import com.backend.user.entity.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T21:16:41+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class OpportunityMapperImpl implements OpportunityMapper {

    @Override
    public Opportunity toOpportunity(OpportunityRegister opportunityRegister) {
        if ( opportunityRegister == null ) {
            return null;
        }

        Opportunity opportunity = new Opportunity();

        opportunity.setTitle( opportunityRegister.title() );
        opportunity.setDescription( opportunityRegister.description() );
        opportunity.setCategory( opportunityRegister.category() );
        opportunity.setLocation( opportunityRegister.location() );
        opportunity.setCity( opportunityRegister.city() );
        opportunity.setSkillsRequired( opportunityRegister.skillsRequired() );
        opportunity.setVolunteerCount( opportunityRegister.volunteerCount() );
        opportunity.setStartDate( opportunityRegister.startDate() );
        opportunity.setEndDate( opportunityRegister.endDate() );

        return opportunity;
    }

    @Override
    public OpportunityDetails toOpportunityDetails(Opportunity opportunity) {
        if ( opportunity == null ) {
            return null;
        }

        Long userId = null;
        Long opportunityId = null;
        String title = null;
        String description = null;
        String category = null;
        String location = null;
        String city = null;
        String skillsRequired = null;
        Integer volunteerCount = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String photoUrl = null;

        userId = opportunityUserId( opportunity );
        opportunityId = opportunity.getId();
        title = opportunity.getTitle();
        description = opportunity.getDescription();
        category = opportunity.getCategory();
        location = opportunity.getLocation();
        city = opportunity.getCity();
        skillsRequired = opportunity.getSkillsRequired();
        volunteerCount = opportunity.getVolunteerCount();
        startDate = opportunity.getStartDate();
        endDate = opportunity.getEndDate();
        photoUrl = opportunity.getPhotoUrl();

        OpportunityDetails opportunityDetails = new OpportunityDetails( userId, opportunityId, title, description, category, location, city, skillsRequired, volunteerCount, startDate, endDate, photoUrl );

        return opportunityDetails;
    }

    private Long opportunityUserId(Opportunity opportunity) {
        if ( opportunity == null ) {
            return null;
        }
        User user = opportunity.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
