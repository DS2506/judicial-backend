package com.example.judicial_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cases/search")
public class SearchController {

    private final CaseRepository caseRepository;

    public SearchController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @GetMapping
    public List<Case> searchCases(
            @RequestParam String caseNumber) {

        return caseRepository.findAll()
                .stream()
                .filter(c -> c.getCaseNumber() != null &&
                        c.getCaseNumber()
                                .toLowerCase()
                                .contains(caseNumber.toLowerCase()))
                .toList();
    }
}