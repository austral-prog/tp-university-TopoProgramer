package com.university.tp2.typesOfExams;

import com.university.entities.Evaluation;
import java.util.List;

public class FinalExam extends Evaluation {
    private int id;

    public FinalExam(String evaluationName) {
        super(evaluationName);
    }

    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.stream().mapToDouble(Double::doubleValue).sum();
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
