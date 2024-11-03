package com.university.Data;

import java.util.List;

public abstract class evaluation {
    protected String evaluationName;

    public evaluation(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public abstract double calculateGrade(List<Double> grades);
}