package com.university.tp2.typesOfExams;

import com.university.entities.Evaluation;
import java.util.List;

public class PracticalWork extends Evaluation {
    private int id;

    public PracticalWork(String evaluationName) {
        super(evaluationName);
    }

    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.isEmpty() ? 0.0 : grades.get(grades.size() - 1);
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
