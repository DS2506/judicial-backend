package com.example.judicial_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseNumber;
    private String caseType;
    private String severity;
    private String filingDate;
    private boolean vulnerableVictim;
    private boolean bailRelated;

    private int priorityScore;
    private String priorityLevel;

    public Case() {
    }

    public Long getId() {
        return id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public boolean isVulnerableVictim() {
        return vulnerableVictim;
    }

    public void setVulnerableVictim(boolean vulnerableVictim) {
        this.vulnerableVictim = vulnerableVictim;
    }

    public boolean isBailRelated() {
        return bailRelated;
    }

    public void setBailRelated(boolean bailRelated) {
        this.bailRelated = bailRelated;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}