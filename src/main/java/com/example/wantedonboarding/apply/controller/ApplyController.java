package com.example.wantedonboarding.apply.controller;

import com.example.wantedonboarding.apply.dto.ApplyDto;
import com.example.wantedonboarding.apply.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/apply")
@RestController
public class ApplyController {

    private final ApplyService applyService;

    @Autowired
    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    @PostMapping("/apply-job")
    public ResponseEntity<String> applyForJob(@RequestBody ApplyDto dto) {
        applyService.applyForJob(dto);
        return ResponseEntity.ok("지원이 완료되었습니다");
    }
}
