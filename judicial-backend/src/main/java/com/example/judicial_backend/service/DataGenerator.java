package com.example.judicial_backend.service;

import com.example.judicial_backend.Case;
import com.example.judicial_backend.CaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class DataGenerator implements CommandLineRunner {

    private final CaseRepository caseRepository;
    private final PriorityService priorityService;

    private final Random random = new Random();

    public DataGenerator(CaseRepository caseRepository,
                          PriorityService priorityService) {
        this.caseRepository = caseRepository;
        this.priorityService = priorityService;
    }

    @Override
    public void run(String... args) {

        // Generate cases if fewer than 200 exist
        generateCases();

        // Recalculate priority for all existing cases
        recalculateExistingCases();
    }

    // Generate 200 sample cases
    public void generateCases() {

        // Do not generate again if 200 or more cases already exist
        if (caseRepository.count() >= 200) {
            return;
        }

        // Generate only enough cases to reach 200
        long existingCases = caseRepository.count();

        for (int i = (int) existingCases + 1; i <= 200; i++) {

            Case c = new Case();

            // Basic case information
            c.setCaseNumber("CASE-" + String.format("%04d", i));

            c.setCaseType(getRandomCaseType());

            c.setSeverity(getRandomSeverity());

            c.setOffenceSeverity(getRandomOffenceSeverity());

            // Filing date
            LocalDate filingDate =
                    LocalDate.now().minusDays(random.nextInt(2000));

            c.setFilingDate(filingDate.toString());

            // Victim information
            c.setVictimAge(10 + random.nextInt(71));

            c.setNumberOfVictims(1 + random.nextInt(10));

            c.setVulnerableVictim(
                    c.getVictimAge() < 18 ||
                    c.getVictimAge() >= 60 ||
                    random.nextInt(10) < 2
            );

            // Bail information
            c.setBailRelated(random.nextBoolean());

            // Accused information
            c.setAccusedAge(18 + random.nextInt(63));

            c.setMedicalCondition(getRandomMedicalCondition());

            c.setOtherCasesCount(random.nextInt(7));

            // Hearing information
            c.setNumberOfHearings(1 + random.nextInt(25));

            c.setNumberOfAdjournments(random.nextInt(12));

            // Next hearing date
            LocalDate hearingDate =
                    LocalDate.now().plusDays(random.nextInt(120) - 10);

            c.setHearingDate(hearingDate.toString());

            // Statutory deadline
            LocalDate deadline =
                    LocalDate.now().plusDays(random.nextInt(180) - 30);

            c.setStatutoryDeadline(deadline.toString());

            // Case progress
            c.setCurrentStage(getRandomStage());

            c.setDelayRisk(getRandomDelayRisk());

            c.setEvidenceStatus(getRandomEvidenceStatus());

            // Calculate priority using PriorityService
            priorityService.assignPriority(c);

            // Save case
            caseRepository.save(c);
        }
    }

    // Recalculate priority for all existing cases
    private void recalculateExistingCases() {

        var cases = caseRepository.findAll();

        for (Case c : cases) {
            priorityService.assignPriority(c);
        }

        caseRepository.saveAll(cases);
    }

    private String getRandomCaseType() {

        String[] types = {
                "Murder",
                "Rape",
                "Kidnapping",
                "Robbery",
                "Assault",
                "Theft",
                "Fraud",
                "Cheating",
                "Cyber Crime",
                "Property Dispute"
        };

        return types[random.nextInt(types.length)];
    }

    private String getRandomSeverity() {

        String[] severity = {
                "CRITICAL",
                "HIGH",
                "MEDIUM",
                "LOW"
        };

        return severity[random.nextInt(severity.length)];
    }

    private String getRandomOffenceSeverity() {

        String[] severity = {
                "CRITICAL",
                "HIGH",
                "MEDIUM",
                "LOW"
        };

        return severity[random.nextInt(severity.length)];
    }

    private String getRandomMedicalCondition() {

        String[] conditions = {
                "NONE",
                "NONE",
                "NONE",
                "DIABETES",
                "ASTHMA",
                "CHRONIC",
                "HEART DISEASE",
                "KIDNEY DISEASE",
                "CANCER",
                "SERIOUS",
                "CRITICAL",
                "DISABILITY"
        };

        return conditions[random.nextInt(conditions.length)];
    }

    private String getRandomStage() {

        String[] stages = {
                "INVESTIGATION",
                "PRE-TRIAL",
                "CHARGE",
                "TRIAL",
                "EVIDENCE",
                "ARGUMENT",
                "JUDGMENT"
        };

        return stages[random.nextInt(stages.length)];
    }

    private String getRandomDelayRisk() {

        String[] risks = {
                "NONE",
                "LOW",
                "MEDIUM",
                "HIGH",
                "VERY HIGH"
        };

        return risks[random.nextInt(risks.length)];
    }

    private String getRandomEvidenceStatus() {

        String[] statuses = {
                "READY",
                "EVIDENCE READY",
                "MOSTLY READY",
                "PARTIALLY READY",
                "PENDING"
        };

        return statuses[random.nextInt(statuses.length)];
    }
}