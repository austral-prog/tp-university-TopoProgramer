package com.university.entities;

import com.university.cli.interfaces.Entity;

import java.util.List;

public abstract class Evaluation implements Entity {
    protected String evaluationName;


    public Evaluation(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public abstract double calculateGrade(List<Double> grades);
}