package com.dayone.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    // 검색할때 api
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        return null;
    }

    // 회사목록 조회 api
    @GetMapping
    public ResponseEntity<?> searchCompany() {
        return null;
    }

    // 저장
    @PostMapping
    public ResponseEntity<?> addCompany() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
