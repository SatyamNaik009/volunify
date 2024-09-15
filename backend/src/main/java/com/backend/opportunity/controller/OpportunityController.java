package com.backend.opportunity.controller;

import com.backend.opportunity.dto.OpportunityDetails;
import com.backend.opportunity.dto.OpportunityList;
import com.backend.opportunity.dto.OpportunityRegister;
import com.backend.opportunity.service.interfac.OpportunityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
@CrossOrigin
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @PostMapping(value = "/admin/opportunity/add/{userId}", consumes = {"multipart/form-data"})
    public ResponseEntity<OpportunityDetails> addOpportunity(@RequestPart("opportunityRegister") String opportunityRegister, @RequestPart("file") MultipartFile file, @PathVariable Long userId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        OpportunityRegister opportunityRegisterObj = objectMapper.readValue(opportunityRegister, OpportunityRegister.class);
        OpportunityDetails opportunityDetails = opportunityService.addOpportunity(opportunityRegisterObj,file, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunityDetails);
    }

    @GetMapping("/user/opportunity/{userId}")
    public ResponseEntity<OpportunityList> getAllOpportunities(@PathVariable Long userId) {
        OpportunityList opportunityDetailsList = opportunityService.getAllOpportunities(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunityDetailsList);
    }

    @GetMapping("/user/opportunity/get/{opportunityId}")
    public ResponseEntity<OpportunityDetails> getOpportunity(@PathVariable Long userId) {
        OpportunityDetails opportunityDetails = opportunityService.getOpportunity(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunityDetails);
    }

    @GetMapping("/opportunity/{city}")
    public ResponseEntity<OpportunityList> getAllOpportunitiesByCity(@PathVariable String city) {
        OpportunityList opportunityDetailsList = opportunityService.getAllOpportunitiesByCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunityDetailsList);
    }

}
