package com.example.autotest.controller;

import com.example.autotest.service.GenerateTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    GenerateTestService generateTestService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateTest(@RequestParam String testInstruction){
        return ResponseEntity.ok(generateTestService.generateScript(testInstruction));
    }
}
