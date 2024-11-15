package com.university.tp2.examManager;



import com.university.entities.Evaluation;

import java.util.List;

public class GradeCalculator {

    public double calculateGrade(List<Double> grades, Evaluation evaluation) {
        return evaluation.calculateGrade(grades);
    }
}