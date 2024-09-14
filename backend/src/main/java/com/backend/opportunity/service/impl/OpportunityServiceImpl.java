package com.backend.opportunity.service.impl;

import com.backend.opportunity.dto.OpportunityDetails;
import com.backend.opportunity.dto.OpportunityList;
import com.backend.opportunity.dto.OpportunityRegister;
import com.backend.opportunity.entity.Opportunity;
import com.backend.opportunity.mapper.OpportunityMapper;
import com.backend.opportunity.repository.OpportunityRepository;
import com.backend.opportunity.service.interfac.OpportunityService;
import com.backend.user.entity.User;
import com.backend.user.repository.UserRepository;
import com.backend.user.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpportunityServiceImpl implements OpportunityService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OpportunityMapper opportunityMapper;

    @Autowired
    private AwsS3Service awsS3Service;

    @Override
    public OpportunityDetails addOpportunity(OpportunityRegister opportunityRegister, MultipartFile file, Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Opportunity opportunity=opportunityMapper.toOpportunity(opportunityRegister);
        opportunity.setUser(user);

        String photoUrl = awsS3Service.saveImageToS3(file);
        opportunity.setPhotoUrl(photoUrl);

        Opportunity savedOpportunity=opportunityRepository.save(opportunity);
        return opportunityMapper.toOpportunityDetails(savedOpportunity);
    }

    @Override
    public OpportunityList getAllOpportunities(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Opportunity> opportunities=opportunityRepository.findAllByUserId(userId);
        List<OpportunityDetails> opportunityDetailsList=opportunities.stream()
                .map(opportunityMapper::toOpportunityDetails)
                .collect(Collectors.toList());
        return opportunityMapper.toOpportunityList(opportunityDetailsList);
    }

    @Override
    public OpportunityList getAllOpportunities() {
        List<Opportunity> opportunities=opportunityRepository.findAll();
        List<OpportunityDetails> opportunityDetailsList=opportunities.stream()
                .map(opportunityMapper::toOpportunityDetails)
                .collect(Collectors.toList());
        return opportunityMapper.toOpportunityList(opportunityDetailsList);
    }

    @Override
    public OpportunityDetails getOpportunity(Long opportunityId) {
        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new IllegalArgumentException("Opportunity not found"));
        return opportunityMapper.toOpportunityDetails(opportunity);
    }

    @Override
    public List<String> getAllCities() {
        List<String> cities=opportunityRepository.findAllDistinctCities();
        return cities;
    }
}
