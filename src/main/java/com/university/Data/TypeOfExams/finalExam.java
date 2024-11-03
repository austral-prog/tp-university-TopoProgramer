package com.university.Data.TypeOfExams; // Declaración del paquete de la clase

import com.university.Data.evaluation;

import java.util.List; // Importa la clase List para manejar listas de notas

// Clase que representa un examen final, extiende Evaluation
public class finalExam extends evaluation {
    public finalExam(String evaluationName) {
        super(evaluationName);
    }
    @Override
    public double calculateGrade(List<Double> grades) {
        return grades.stream().mapToDouble(Double::doubleValue).sum();
    }
}