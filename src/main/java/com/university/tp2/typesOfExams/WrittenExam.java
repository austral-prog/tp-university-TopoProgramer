package com.university.tp2.typesOfExams;

import com.university.entities.Evaluation;
import java.util.List;

public class WrittenExam extends Evaluation {
    private int id; // Agrega un campo id

    public WrittenExam(String evaluationName) {
        super(evaluationName);
    }

    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.isEmpty() ? 0.0 : grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
