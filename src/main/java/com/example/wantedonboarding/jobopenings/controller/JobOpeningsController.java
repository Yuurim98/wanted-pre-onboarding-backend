package com.example.wantedonboarding.jobopenings.controller;

import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.engine.valueextraction.ArrayElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("list")
    public ResponseEntity<Page<JobOpeningsDto>> jobOpeningList(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<JobOpeningsDto> jobOpeningList =openingsService.getAllJobOpeningList(page, size);
        if (jobOpeningList.isEmpty()) {
            return ResponseEntity.noContent().build(); //리스트가 비어있을 때
        }
        return ResponseEntity.ok(jobOpeningList);
    }
}
