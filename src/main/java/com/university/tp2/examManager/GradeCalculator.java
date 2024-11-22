package com.university.tp2.examManager;

import com.university.entities.Evaluation;
import java.util.List;

public class GradeCalculator {

    /**
     * Calcula la nota final basada en una lista de notas y el tipo de evaluación.
     *
     * @param grades la lista de notas del estudiante
     * @param evaluation el objeto Evaluation que define cómo calcular la nota final
     * @return la nota final calculada
     */
    public double calculateGrade(List<Double> grades, Evaluation evaluation) {
        return evaluation.calculateGrade(grades); // Delegar el cálculo de la nota al objeto Evaluation
    }
}
