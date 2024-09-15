package com.backend.user.controller;

import com.backend.opportunity.dto.OpportunityList;
import com.backend.opportunity.service.interfac.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.user.dto.LoginRequest;
import com.backend.user.dto.LoginResponse;
import com.backend.user.dto.Register;
import com.backend.user.dto.UserProfile;
import com.backend.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin

public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OpportunityService opportunityService;

    @PostMapping("/register")
    public ResponseEntity<UserProfile> register(@RequestBody Register register) throws Exception {
        UserProfile response = userService.register(register);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<OpportunityList> getAllOpportunity() {
        OpportunityList opportunityList = opportunityService.getAllOpportunities();
        return ResponseEntity.ok(opportunityList);
    }

    @GetMapping("/getCities")
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities = opportunityService.getAllCities();
        return ResponseEntity.ok(cities);
    }
}
