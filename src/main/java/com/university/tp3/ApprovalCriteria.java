package com.university.tp3;

import java.util.List;

public class ApprovalCriteria {

    private String subject;
    private String criteriaType; // Tipo de evaluación (AVERAGE_ABOVE_VALUE, MAX_ABOVE_VALUE, MIN_ABOVE_VALUE)
    private double criteriaValue; // El valor mínimo necesario para aprobar
    private List<String> evaluationNames; // Nombres de las evaluaciones que se consideran

    // Constructor
    public ApprovalCriteria(String subject, String criteriaType, double criteriaValue, List<String> evaluationNames) {
        this.subject = subject;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.evaluationNames = evaluationNames;
    }

    // Getters y setters
    public String getSubject() {
        return subject;
    }

    public String getCriteriaType() {
        return criteriaType;
    }

    public double getCriteriaValue() {
        return criteriaValue;
    }

    public List<String> getEvaluationNames() {
        return evaluationNames;
    }
}
