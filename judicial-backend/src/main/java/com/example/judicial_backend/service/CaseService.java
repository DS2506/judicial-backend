package com.example.judicial_backend.service;

import com.example.judicial_backend.Case;
import com.example.judicial_backend.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    private final CaseRepository caseRepository;
    private final PriorityService priorityService;

    public CaseService(CaseRepository caseRepository,
                       PriorityService priorityService) {
        this.caseRepository = caseRepository;
        this.priorityService = priorityService;
    }

    // =========================================================
    // CREATE CASE
    // =========================================================

    public Case createCase(Case c) {

        // Calculate priority automatically
        priorityService.assignPriority(c);

        // Save case into database
        return caseRepository.save(c);
    }


    // =========================================================
    // GET ALL CASES
    // =========================================================

    public List<Case> getAllCases() {

        return caseRepository.findAll();
    }


    // =========================================================
    // GET CASE BY ID
    // =========================================================

    public Optional<Case> getCaseById(Long id) {

        return caseRepository.findById(id);
    }


    // =========================================================
    // UPDATE CASE
    // =========================================================

    public Case updateCase(Long id, Case updatedCase) {

        Optional<Case> existingCase =
                caseRepository.findById(id);

        if (existingCase.isPresent()) {

            Case c = existingCase.get();

            // Update basic case information
            c.setCaseNumber(updatedCase.getCaseNumber());
            c.setCaseType(updatedCase.getCaseType());
            c.setSeverity(updatedCase.getSeverity());
            c.setOffenceSeverity(updatedCase.getOffenceSeverity());
            c.setFilingDate(updatedCase.getFilingDate());

            // Update victim information
            c.setVulnerableVictim(
                    updatedCase.isVulnerableVictim()
            );

            c.setVictimAge(
                    updatedCase.getVictimAge()
            );

            c.setNumberOfVictims(
                    updatedCase.getNumberOfVictims()
            );

            // Update bail information
            c.setBailRelated(
                    updatedCase.isBailRelated()
            );

            // Update accused information
            c.setAccusedAge(
                    updatedCase.getAccusedAge()
            );

            c.setMedicalCondition(
                    updatedCase.getMedicalCondition()
            );

            c.setOtherCasesCount(
                    updatedCase.getOtherCasesCount()
            );

            // Update hearing information
            c.setNumberOfHearings(
                    updatedCase.getNumberOfHearings()
            );

            c.setNumberOfAdjournments(
                    updatedCase.getNumberOfAdjournments()
            );

            c.setHearingDate(
                    updatedCase.getHearingDate()
            );

            // Update urgency information
            c.setStatutoryDeadline(
                    updatedCase.getStatutoryDeadline()
            );

            // Update case progress
            c.setCurrentStage(
                    updatedCase.getCurrentStage()
            );

            c.setDelayRisk(
                    updatedCase.getDelayRisk()
            );

            c.setEvidenceStatus(
                    updatedCase.getEvidenceStatus()
            );

            // Recalculate priority
            priorityService.assignPriority(c);

            // Save updated case
            return caseRepository.save(c);
        }

        return null;
    }


    // =========================================================
    // DELETE CASE
    // =========================================================

    public boolean deleteCase(Long id) {

        if (caseRepository.existsById(id)) {

            caseRepository.deleteById(id);

            return true;
        }

        return false;
    }


    // =========================================================
    // GET CASES SORTED BY PRIORITY
    // =========================================================

    public List<Case> getCasesByPriority() {

        List<Case> cases =
                caseRepository.findAll();

        // Sort highest priority score first
        cases.sort(
                (c1, c2) ->
                        Integer.compare(
                                c2.getPriorityScore(),
                                c1.getPriorityScore()
                        )
        );

        return cases;
    }
}