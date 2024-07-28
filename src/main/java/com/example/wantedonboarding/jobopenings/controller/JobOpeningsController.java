package com.example.wantedonboarding.jobopenings.controller;

import com.example.wantedonboarding.jobopenings.dto.JobOpeningUpdateDto;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.engine.valueextraction.ArrayElement;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("post") //공고 등록
    public ResponseEntity<?> PostJob(@Valid @RequestBody JobOpeningsDto dto) {
        //DTO를 서비스에 전달하여 처리
        JobOpeningsDto createdDto = openingsService.createJobOpening(dto);
        return ResponseEntity.ok(createdDto);
    }


    @PatchMapping("update/{openingId}")
    public ResponseEntity<?> Update(@PathVariable Long openingId, @RequestBody JobOpeningUpdateDto updateDto) {
        JobOpeningsDto JobOpeningUpdateDto = openingsService.updateJobOpening(openingId, updateDto);
        return ResponseEntity.ok(JobOpeningUpdateDto);
    }

    @DeleteMapping("delete/{openingId}")
    public ResponseEntity<String > deleteJobOpening(@PathVariable Long openingId) {
        openingsService.deleteJobOpening(openingId);
        return ResponseEntity.ok("채용 공고가 삭제되었습니다");
    }

    @GetMapping("list")
    public ResponseEntity<List<JobOpeningsDto>> jobOpeningList() {
        List<JobOpeningsDto> jobOpeningList =openingsService.getAllJobOpeningList();
        if (jobOpeningList.isEmpty()) {
            return ResponseEntity.noContent().build(); //리스트가 비어있을 때
        }
        return ResponseEntity.ok(jobOpeningList);
    }

}
