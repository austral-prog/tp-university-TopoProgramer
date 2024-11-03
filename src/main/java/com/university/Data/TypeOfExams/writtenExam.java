package com.university.Data.TypeOfExams;

import com.university.Data.evaluation;

import java.util.List;

// Clase que representa un examen escrito, extiende Evaluation
public class writtenExam extends evaluation {
    public writtenExam(String evaluationName) {
        super(evaluationName);
    }
    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.isEmpty() ? 0.0 : grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
