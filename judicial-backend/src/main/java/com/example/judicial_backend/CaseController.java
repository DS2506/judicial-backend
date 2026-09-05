package com.example.judicial_backend;

import com.example.judicial_backend.service.CaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    // Get all cases
    @GetMapping
    public List<Case> getAllCases() {
        return caseService.getAllCases();
    }

    // Get a single case by ID
    // \d+ ensures only numeric IDs match this endpoint
    @GetMapping("/{id:\\d+}")
    public Case getCaseById(@PathVariable Long id) {
        return caseService.getCaseById(id).orElse(null);
    }

    // Add a new case
    @PostMapping
    public Case addCase(@RequestBody Case newCase) {
        return caseService.createCase(newCase);
    }

    // Update an existing case
    @PutMapping("/{id:\\d+}")
    public Case updateCase(
            @PathVariable Long id,
            @RequestBody Case updatedCase) {

        return caseService.updateCase(id, updatedCase);
    }

    // Delete a case
    @DeleteMapping("/{id:\\d+}")
    public String deleteCase(@PathVariable Long id) {

        boolean deleted = caseService.deleteCase(id);

        if (deleted) {
            return "Case deleted successfully";
        }

        return "Case not found";
    }

    // Get cases sorted by priority score
    @GetMapping("/prioritized")
    public List<Case> getPrioritizedCases() {
        return caseService.getCasesByPriority();
    }
}