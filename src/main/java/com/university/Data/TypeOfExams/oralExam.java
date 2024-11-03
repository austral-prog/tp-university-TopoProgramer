package com.university.Data.TypeOfExams;

import com.university.Data.evaluation;

import java.util.List;

// Clase que representa un examen oral, extiende Evaluation
public class oralExam extends evaluation {
    public oralExam(String evaluationName) {
        super(evaluationName);
    }
    @Override
    public double calculateGrade(List<Double> grades) {

        return grades.isEmpty() ? 0.0 : grades.get(0);
    }
}