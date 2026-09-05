package com.example.judicial_backend.service;

import com.example.judicial_backend.Case;
import com.example.judicial_backend.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final CaseRepository caseRepository;

    public DashboardService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    // Get total number of cases
    public long getTotalCases() {
        return caseRepository.count();
    }

    // Get count of cases based on priority level
    public long getHighPriorityCases() {
        return caseRepository.findAll()
                .stream()
                .filter(c -> "HIGH".equalsIgnoreCase(c.getPriorityLevel()))
                .count();
    }

    public long getMediumPriorityCases() {
        return caseRepository.findAll()
                .stream()
                .filter(c -> "MEDIUM".equalsIgnoreCase(c.getPriorityLevel()))
                .count();
    }

    public long getNormalPriorityCases() {
        return caseRepository.findAll()
                .stream()
                .filter(c -> "NORMAL".equalsIgnoreCase(c.getPriorityLevel()))
                .count();
    }

    // Get number of cases for each case type
    public Map<String, Long> getCaseTypeCounts() {

        List<Case> cases = caseRepository.findAll();

        Map<String, Long> caseTypeCounts = new HashMap<>();

        for (Case c : cases) {

            String caseType = c.getCaseType();

            if (caseType == null || caseType.isEmpty()) {
                caseType = "Unknown";
            }

            caseTypeCounts.put(
                    caseType,
                    caseTypeCounts.getOrDefault(caseType, 0L) + 1
            );
        }

        return caseTypeCounts;
    }

    // Get number of cases for each severity
    public Map<String, Long> getSeverityCounts() {

        List<Case> cases = caseRepository.findAll();

        Map<String, Long> severityCounts = new HashMap<>();

        for (Case c : cases) {

            String severity = c.getSeverity();

            if (severity == null || severity.isEmpty()) {
                severity = "Unknown";
            }

            severityCounts.put(
                    severity,
                    severityCounts.getOrDefault(severity, 0L) + 1
            );
        }

        return severityCounts;
    }

    // Get all dashboard statistics together
    public Map<String, Object> getDashboardStatistics() {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("totalCases", getTotalCases());
        dashboard.put("highPriority", getHighPriorityCases());
        dashboard.put("mediumPriority", getMediumPriorityCases());
        dashboard.put("normalPriority", getNormalPriorityCases());
        dashboard.put("caseTypeCounts", getCaseTypeCounts());
        dashboard.put("severityCounts", getSeverityCounts());

        return dashboard;
    }
}