package com.example.wantedonboarding.jobopenings.controller;

import com.example.wantedonboarding.jobopenings.dto.JobOpeningUpdateDto;
import com.example.wantedonboarding.jobopenings.dto.JobOpeningsDto;
import com.example.wantedonboarding.jobopenings.service.JobOpeningsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    @PostMapping("post") //공고 등록
    public ResponseEntity<?> postJob(@Valid @RequestBody JobOpeningsDto dto) {
        //DTO를 서비스에 전달하여 처리
        JobOpeningsDto createdDto = openingsService.createJobOpening(dto);
        return ResponseEntity.ok(createdDto);
    }


    @PatchMapping("update/{openingId}")
    public ResponseEntity<?> update(@PathVariable Long openingId, @RequestBody JobOpeningUpdateDto updateDto) {
        JobOpeningsDto JobOpeningUpdateDto = openingsService.updateJobOpening(openingId, updateDto);
        return ResponseEntity.ok(JobOpeningUpdateDto);
    }

    @DeleteMapping("delete/{openingId}")
    public ResponseEntity<String > deleteJobOpening(@PathVariable Long openingId) {
        openingsService.deleteJobOpening(openingId);
        return ResponseEntity.ok("채용 공고가 삭제되었습니다");
    }

    @GetMapping("list")
    public ResponseEntity<Page<JobOpeningsDto>> jobOpeningList(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size) {
        Page<JobOpeningsDto> jobOpeningList =openingsService.getAllJobOpeningList(page, size);
        if (jobOpeningList.isEmpty()) {
            return ResponseEntity.noContent().build(); //비어있을 때
        }
        return ResponseEntity.ok(jobOpeningList);
    }

    @GetMapping("posting-detail/{openingId}")
    public ResponseEntity<JobOpeningsDto> jobPostingDetailPage(@PathVariable Long openingId) {
        JobOpeningsDto jobOpeningsDto = openingsService.getDetailPage(openingId);
        return ResponseEntity.ok(jobOpeningsDto);
    }


    @GetMapping("search-jobOpening")
    public ResponseEntity<Page<JobOpeningsDto>> searchJobOpening(@RequestParam(defaultValue = "companyName") String searchType,
                                                           @RequestParam String search,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {

        Page<JobOpeningsDto> result = openingsService.getSearchContent(page, size, searchType, search);
        return ResponseEntity.ok(result);
    }

}
