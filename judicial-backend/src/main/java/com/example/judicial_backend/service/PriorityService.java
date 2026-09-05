package com.example.judicial_backend.service;

import com.example.judicial_backend.Case;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class PriorityService {

    // =========================================================
    // CALCULATE TOTAL PRIORITY SCORE
    // Maximum Score = 100
    // =========================================================

    public int calculatePriorityScore(Case c) {

        int score = 0;

        // 1. Case Severity - 10 points
        score += calculateCaseSeverity(c.getSeverity());

        // 2. Offence Severity - 10 points
        score += calculateOffenceSeverity(c.getOffenceSeverity());

        // 3. Case Age - 10 points
        score += calculateCaseAge(c.getFilingDate());

        // 4. Number of Adjournments - 8 points
        score += calculateAdjournments(c.getNumberOfAdjournments());

        // 5. Hearing Urgency - 8 points
        score += calculateHearingUrgency(c.getHearingDate());

        // 6. Statutory / Time-limit Urgency - 8 points
        score += calculateStatutoryUrgency(c.getStatutoryDeadline());

        // 7. Number of Hearings - 5 points
        score += calculateNumberOfHearings(c.getNumberOfHearings());

        // 8. Vulnerable Victim + Victim Age - 8 points
        score += calculateVictimPriority(
                c.isVulnerableVictim(),
                c.getVictimAge()
        );

        // 9. Number of Victims - 5 points
        score += calculateNumberOfVictims(c.getNumberOfVictims());

        // 10. Accused Age - 3 points
        score += calculateAccusedAge(c.getAccusedAge());

        // 11. Medical Condition - 5 points
        score += calculateMedicalCondition(c.getMedicalCondition());

        // 12. Other Cases Involving Accused - 4 points
        score += calculateOtherCases(c.getOtherCasesCount());

        // 13. Evidence Status - 5 points
        score += calculateEvidenceStatus(c.getEvidenceStatus());

        // 14. Current Stage - 5 points
        score += calculateCurrentStage(c.getCurrentStage());

        // 15. Delay Risk - 6 points
        score += calculateDelayRisk(c.getDelayRisk());

        // Safety check
        if (score > 100) {
            score = 100;
        }

        if (score < 0) {
            score = 0;
        }

        return score;
    }


    // =========================================================
    // 1. CASE SEVERITY - MAXIMUM 10 POINTS
    // =========================================================

    private int calculateCaseSeverity(String severity) {

        if (severity == null) {
            return 0;
        }

        switch (severity.toUpperCase()) {

            case "CRITICAL":
                return 10;

            case "HIGH":
                return 8;

            case "MEDIUM":
                return 5;

            case "LOW":
                return 2;

            default:
                return 0;
        }
    }


    // =========================================================
    // 2. OFFENCE SEVERITY - MAXIMUM 10 POINTS
    // =========================================================

    private int calculateOffenceSeverity(String offenceSeverity) {

        if (offenceSeverity == null) {
            return 0;
        }

        switch (offenceSeverity.toUpperCase()) {

            case "CRITICAL":
                return 10;

            case "HIGH":
                return 8;

            case "MEDIUM":
                return 5;

            case "LOW":
                return 2;

            default:
                return 0;
        }
    }


    // =========================================================
    // 3. CASE AGE - MAXIMUM 10 POINTS
    // =========================================================

    private int calculateCaseAge(String filingDate) {

        if (filingDate == null || filingDate.isEmpty()) {
            return 0;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date =
                    LocalDate.parse(filingDate, formatter);

            LocalDate today = LocalDate.now();

            long days =
                    ChronoUnit.DAYS.between(date, today);

            // More than 5 years
            if (days > 1825) {
                return 10;
            }

            // 3 to 5 years
            else if (days > 1095) {
                return 8;
            }

            // 1 to 3 years
            else if (days > 365) {
                return 6;
            }

            // 6 months to 1 year
            else if (days > 180) {
                return 4;
            }

            // Less than 6 months
            else {
                return 2;
            }

        } catch (Exception e) {
            return 0;
        }
    }


    // =========================================================
    // 4. NUMBER OF ADJOURNMENTS - MAXIMUM 8 POINTS
    // =========================================================

    private int calculateAdjournments(int adjournments) {

        if (adjournments > 10) {
            return 8;
        }

        else if (adjournments >= 6) {
            return 6;
        }

        else if (adjournments >= 3) {
            return 4;
        }

        else if (adjournments >= 1) {
            return 2;
        }

        else {
            return 0;
        }
    }


    // =========================================================
    // 5. HEARING URGENCY - MAXIMUM 8 POINTS
    // =========================================================

    private int calculateHearingUrgency(String hearingDate) {

        if (hearingDate == null || hearingDate.isEmpty()) {
            return 0;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate hearing =
                    LocalDate.parse(hearingDate, formatter);

            LocalDate today = LocalDate.now();

            long days =
                    ChronoUnit.DAYS.between(today, hearing);

            // Hearing already overdue
            if (days < 0) {
                return 8;
            }

            // Hearing within 7 days
            else if (days <= 7) {
                return 7;
            }

            // Hearing within 30 days
            else if (days <= 30) {
                return 5;
            }

            // Hearing within 90 days
            else if (days <= 90) {
                return 3;
            }

            // More than 90 days
            else {
                return 1;
            }

        } catch (Exception e) {
            return 0;
        }
    }


    // =========================================================
    // 6. STATUTORY / TIME-LIMIT URGENCY - MAXIMUM 8 POINTS
    // =========================================================

    private int calculateStatutoryUrgency(String deadline) {

        if (deadline == null || deadline.isEmpty()) {
            return 0;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate deadlineDate =
                    LocalDate.parse(deadline, formatter);

            LocalDate today = LocalDate.now();

            long days =
                    ChronoUnit.DAYS.between(today, deadlineDate);

            // Deadline already passed
            if (days < 0) {
                return 8;
            }

            // Deadline within 30 days
            else if (days <= 30) {
                return 6;
            }

            // Deadline within 90 days
            else if (days <= 90) {
                return 4;
            }

            // No immediate urgency
            else {
                return 0;
            }

        } catch (Exception e) {
            return 0;
        }
    }


    // =========================================================
    // 7. NUMBER OF HEARINGS - MAXIMUM 5 POINTS
    // =========================================================

    private int calculateNumberOfHearings(int hearings) {

        if (hearings > 20) {
            return 5;
        }

        else if (hearings >= 11) {
            return 4;
        }

        else if (hearings >= 6) {
            return 3;
        }

        else if (hearings >= 1) {
            return 1;
        }

        else {
            return 0;
        }
    }


    // =========================================================
    // 8. VULNERABLE VICTIM + VICTIM AGE - MAXIMUM 8 POINTS
    // =========================================================

    private int calculateVictimPriority(
            boolean vulnerableVictim,
            int victimAge) {

        // Vulnerable child
        if (vulnerableVictim
                && victimAge > 0
                && victimAge < 18) {

            return 8;
        }

        // Vulnerable elderly person
        if (vulnerableVictim && victimAge >= 60) {
            return 8;
        }

        // Other vulnerable person
        if (vulnerableVictim) {
            return 6;
        }

        // Child
        if (victimAge > 0 && victimAge < 18) {
            return 7;
        }

        // Elderly
        if (victimAge >= 60) {
            return 5;
        }

        // Normal adult victim
        if (victimAge > 0) {
            return 3;
        }

        return 0;
    }


    // =========================================================
    // 9. NUMBER OF VICTIMS - MAXIMUM 5 POINTS
    // =========================================================

    private int calculateNumberOfVictims(int victims) {

        if (victims > 10) {
            return 5;
        }

        else if (victims >= 5) {
            return 4;
        }

        else if (victims >= 2) {
            return 3;
        }

        else if (victims == 1) {
            return 1;
        }

        else {
            return 0;
        }
    }


    // =========================================================
    // 10. ACCUSED AGE - MAXIMUM 3 POINTS
    // =========================================================

    private int calculateAccusedAge(int age) {

        if (age >= 75) {
            return 3;
        }

        else if (age >= 60) {
            return 2;
        }

        else if (age > 0) {
            return 1;
        }

        else {
            return 0;
        }
    }


    // =========================================================
    // 11. MEDICAL CONDITION - MAXIMUM 5 POINTS
    // =========================================================

    private int calculateMedicalCondition(String condition) {

        if (condition == null || condition.isEmpty()) {
            return 0;
        }

        String medical =
                condition.toUpperCase();

        // Critical or life-threatening
        if (medical.contains("CRITICAL")
                || medical.contains("TERMINAL")
                || medical.contains("LIFE THREATENING")) {

            return 5;
        }

        // Serious medical condition
        if (medical.contains("SERIOUS")
                || medical.contains("CANCER")
                || medical.contains("HEART")
                || medical.contains("STROKE")
                || medical.contains("KIDNEY")
                || medical.contains("LIVER")) {

            return 4;
        }

        // Chronic condition
        if (medical.contains("CHRONIC")
                || medical.contains("DIABETES")
                || medical.contains("ASTHMA")
                || medical.contains("DISABILITY")) {

            return 3;
        }

        // Minor condition
        if (medical.contains("MINOR")) {
            return 1;
        }

        return 0;
    }


    // =========================================================
    // 12. OTHER CASES INVOLVING ACCUSED - MAXIMUM 4 POINTS
    // =========================================================

    private int calculateOtherCases(int cases) {

        if (cases > 5) {
            return 4;
        }

        else if (cases >= 3) {
            return 3;
        }

        else if (cases >= 1) {
            return 2;
        }

        else {
            return 0;
        }
    }


    // =========================================================
    // 13. EVIDENCE STATUS - MAXIMUM 5 POINTS
    // =========================================================

    private int calculateEvidenceStatus(String status) {

        if (status == null || status.isEmpty()) {
            return 0;
        }

        String evidence =
                status.toUpperCase();

        if (evidence.equals("READY")
                || evidence.equals("EVIDENCE READY")) {

            return 5;
        }

        else if (evidence.equals("MOSTLY READY")) {
            return 4;
        }

        else if (evidence.equals("PARTIALLY READY")) {
            return 2;
        }

        else if (evidence.equals("PENDING")) {
            return 1;
        }

        return 0;
    }


    // =========================================================
    // 14. CURRENT STAGE - MAXIMUM 5 POINTS
    // =========================================================

    private int calculateCurrentStage(String stage) {

        if (stage == null || stage.isEmpty()) {
            return 0;
        }

        String currentStage =
                stage.toUpperCase();

        if (currentStage.contains("JUDGMENT")) {
            return 5;
        }

        else if (currentStage.contains("ARGUMENT")) {
            return 4;
        }

        else if (currentStage.contains("EVIDENCE")) {
            return 4;
        }

        else if (currentStage.contains("TRIAL")) {
            return 3;
        }

        else if (currentStage.contains("CHARGE")) {
            return 2;
        }

        else if (currentStage.contains("INVESTIGATION")
                || currentStage.contains("PRE-TRIAL")) {

            return 1;
        }

        return 0;
    }


    // =========================================================
    // 15. DELAY RISK - MAXIMUM 6 POINTS
    // =========================================================

    private int calculateDelayRisk(String risk) {

        if (risk == null || risk.isEmpty()) {
            return 0;
        }

        switch (risk.toUpperCase()) {

            case "VERY HIGH":
                return 6;

            case "HIGH":
                return 5;

            case "MEDIUM":
                return 3;

            case "LOW":
                return 1;

            case "NONE":
                return 0;

            default:
                return 0;
        }
    }


    // =========================================================
    // ASSIGN PRIORITY TO CASE
    // =========================================================

    public void assignPriority(Case c) {

        int score =
                calculatePriorityScore(c);

        String level =
                getPriorityLevel(score);

        c.setPriorityScore(score);
        c.setPriorityLevel(level);
    }


    // =========================================================
    // CONVERT SCORE TO PRIORITY LEVEL
    // =========================================================

    public String getPriorityLevel(int score) {

        if (score >= 80) {
            return "CRITICAL";
        }

        else if (score >= 50) {
            return "HIGH";
        }

        else if (score >= 30) {
            return "MEDIUM";
        }

        else {
            return "NORMAL";
        }
    }
}