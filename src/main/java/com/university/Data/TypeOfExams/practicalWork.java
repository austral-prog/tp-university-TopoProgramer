package com.university.Data.TypeOfExams;

import com.university.Data.evaluation;

import java.util.List;

// Clase que representa un trabajo práctico, extiende Evaluation
public class practicalWork extends evaluation {
    public practicalWork(String evaluationName) {
        super(evaluationName);
    }
    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.isEmpty() ? 0.0 : grades.get(grades.size() - 1);
    }
}