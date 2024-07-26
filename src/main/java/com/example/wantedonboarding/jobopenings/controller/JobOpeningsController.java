package com.example.wantedonboarding.jobopenings.controller;

import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.members.service.JobOpeningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobOpeningsController {

    private final JobOpeningsService openingsService;

    @Autowired
    public JobOpeningsController(JobOpeningsService openingsService) {
        this.openingsService = openingsService;
    }


//    @PostMapping("post-job")
//    public ResponseEntity<?> PostJob(@RequestBody JobOpeningsDto dto) {
//
//    }
}
