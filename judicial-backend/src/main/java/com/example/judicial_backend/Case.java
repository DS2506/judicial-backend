package com.example.judicial_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Existing case information
    private String caseNumber;
    private String caseType;
    private String severity;
    private String offenceSeverity;
    private String filingDate;
    private boolean vulnerableVictim;
    private boolean bailRelated;

    // Victim-related priority factors
    private int victimAge;
    private int numberOfVictims;

    // Accused-related priority factors
    private int accusedAge;
    private String medicalCondition;
    private int otherCasesCount;

    // Hearing and delay factors
    private int numberOfHearings;
    private int numberOfAdjournments;
    private String hearingDate;
    private String statutoryDeadline;

    // Case progress factors
    private String currentStage;
    private String delayRisk;
    private String evidenceStatus;

    // Calculated priority
    private int priorityScore;
    private String priorityLevel;


    // Default constructor
    public Case() {
    }


    // =========================================================
    // ID
    // =========================================================

    public Long getId() {
        return id;
    }


    // =========================================================
    // CASE NUMBER
    // =========================================================

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }


    // =========================================================
    // CASE TYPE
    // =========================================================

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }


    // =========================================================
    // CASE SEVERITY
    // =========================================================

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }


    // =========================================================
    // OFFENCE SEVERITY
    // =========================================================

    public String getOffenceSeverity() {
        return offenceSeverity;
    }

    public void setOffenceSeverity(String offenceSeverity) {
        this.offenceSeverity = offenceSeverity;
    }


    // =========================================================
    // FILING DATE
    // =========================================================

    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }


    // =========================================================
    // VULNERABLE VICTIM
    // =========================================================

    public boolean isVulnerableVictim() {
        return vulnerableVictim;
    }

    public void setVulnerableVictim(boolean vulnerableVictim) {
        this.vulnerableVictim = vulnerableVictim;
    }


    // =========================================================
    // BAIL RELATED
    // =========================================================

    public boolean isBailRelated() {
        return bailRelated;
    }

    public void setBailRelated(boolean bailRelated) {
        this.bailRelated = bailRelated;
    }


    // =========================================================
    // VICTIM AGE
    // =========================================================

    public int getVictimAge() {
        return victimAge;
    }

    public void setVictimAge(int victimAge) {
        this.victimAge = victimAge;
    }


    // =========================================================
    // NUMBER OF VICTIMS
    // =========================================================

    public int getNumberOfVictims() {
        return numberOfVictims;
    }

    public void setNumberOfVictims(int numberOfVictims) {
        this.numberOfVictims = numberOfVictims;
    }


    // =========================================================
    // ACCUSED AGE
    // =========================================================

    public int getAccusedAge() {
        return accusedAge;
    }

    public void setAccusedAge(int accusedAge) {
        this.accusedAge = accusedAge;
    }


    // =========================================================
    // MEDICAL CONDITION
    // =========================================================

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }


    // =========================================================
    // OTHER CASES COUNT
    // =========================================================

    public int getOtherCasesCount() {
        return otherCasesCount;
    }

    public void setOtherCasesCount(int otherCasesCount) {
        this.otherCasesCount = otherCasesCount;
    }


    // =========================================================
    // NUMBER OF HEARINGS
    // =========================================================

    public int getNumberOfHearings() {
        return numberOfHearings;
    }

    public void setNumberOfHearings(int numberOfHearings) {
        this.numberOfHearings = numberOfHearings;
    }


    // =========================================================
    // NUMBER OF ADJOURNMENTS
    // =========================================================

    public int getNumberOfAdjournments() {
        return numberOfAdjournments;
    }

    public void setNumberOfAdjournments(int numberOfAdjournments) {
        this.numberOfAdjournments = numberOfAdjournments;
    }


    // =========================================================
    // HEARING DATE
    // =========================================================

    public String getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(String hearingDate) {
        this.hearingDate = hearingDate;
    }


    // =========================================================
    // STATUTORY DEADLINE
    // =========================================================

    public String getStatutoryDeadline() {
        return statutoryDeadline;
    }

    public void setStatutoryDeadline(String statutoryDeadline) {
        this.statutoryDeadline = statutoryDeadline;
    }


    // =========================================================
    // CURRENT STAGE
    // =========================================================

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }


    // =========================================================
    // DELAY RISK
    // =========================================================

    public String getDelayRisk() {
        return delayRisk;
    }

    public void setDelayRisk(String delayRisk) {
        this.delayRisk = delayRisk;
    }


    // =========================================================
    // EVIDENCE STATUS
    // =========================================================

    public String getEvidenceStatus() {
        return evidenceStatus;
    }

    public void setEvidenceStatus(String evidenceStatus) {
        this.evidenceStatus = evidenceStatus;
    }


    // =========================================================
    // PRIORITY SCORE
    // =========================================================

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }


    // =========================================================
    // PRIORITY LEVEL
    // =========================================================

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}