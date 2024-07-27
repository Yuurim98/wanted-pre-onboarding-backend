package com.example.wantedonboarding.jobopenings.controller;

import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.engine.valueextraction.ArrayElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("job-opening")
@RestController
public class JobOpeningsController {

    private final JobOpeningsService openingsService;

    @Autowired
    public JobOpeningsController(JobOpeningsService openingsService) {
        this.openingsService = openingsService;
    }


    @PostMapping("post")
    public ResponseEntity<?> PostJob(@Valid @RequestBody JobOpeningsDto dto) {
        //DTO를 서비스에 전달하여 처리
        JobOpeningsDto createdDto = openingsService.createJobOpening(dto);
        return ResponseEntity.ok(createdDto);
    }




    @DeleteMapping("delete/{openingId}")
    public ResponseEntity<String > deleteJobOpening(@PathVariable Long openingId) {
        openingsService.deleteJobOpening(openingId);
        return ResponseEntity.ok("채용 공고가 삭제되었습니다");
    }
}
