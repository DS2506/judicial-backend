package com.example.judicial_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cases/filter")
public class FilterController {

    private final CaseRepository caseRepository;

    public FilterController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @GetMapping
    public List<Case> filterCases(
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String caseType,
            @RequestParam(required = false) String severity) {

        return caseRepository.findAll()
                .stream()
                .filter(c -> priority == null ||
                        (c.getPriorityLevel() != null &&
                        c.getPriorityLevel().equalsIgnoreCase(priority)))
                .filter(c -> caseType == null ||
                        (c.getCaseType() != null &&
                        c.getCaseType().equalsIgnoreCase(caseType)))
                .filter(c -> severity == null ||
                        (c.getSeverity() != null &&
                        c.getSeverity().equalsIgnoreCase(severity)))
                .toList();
    }
}