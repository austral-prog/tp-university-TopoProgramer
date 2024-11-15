package com.university.tp3;

import java.util.*;

public class CriteriaEvaluator {

    // Evaluar si el estudiante aprueba basado en el criterio y las evaluaciones dadas
    public boolean isApproved(ApprovalCriteria criteria, Map<String, Double> grades) {
        String criteriaType = criteria.getCriteriaType(); // Tipo de criterio, como "AVERAGE_ABOVE_VALUE", "MAX_ABOVE_VALUE", "MIN_ABOVE_VALUE"
        double criteriaValue = criteria.getCriteriaValue(); // El valor mínimo requerido para aprobar
        List<String> evaluationsUsed = getEvaluationsUsed(criteria, grades); // Evaluaciones que el estudiante ha realizado

        if (evaluationsUsed.isEmpty()) {
            return false; // Si no hay evaluaciones disponibles, no aprueba
        }

        // Lógica según el tipo de criterio
        if (criteriaType.equals("AVERAGE_ABOVE_VALUE")) {
            // Si es AVERAGE_ABOVE_VALUE, calculamos el promedio de las evaluaciones
            double sum = 0;
            int count = 0;
            for (String evaluationName : evaluationsUsed) {
                sum += grades.get(evaluationName);
                count++;
            }
            double average = count > 0 ? sum / count : 0;
            return average >= criteriaValue; // El estudiante aprueba si el promedio es mayor o igual al valor del criterio

        } else if (criteriaType.equals("MAX_ABOVE_VALUE")) {
            // Si es MAX_ABOVE_VALUE, tomamos la nota máxima registrada
            double maxGrade = -1;
            for (String evaluationName : evaluationsUsed) {
                double grade = grades.get(evaluationName);
                if (grade > maxGrade) {
                    maxGrade = grade;
                }
            }
            return maxGrade >= criteriaValue; // El estudiante aprueba si la nota máxima es mayor o igual al valor del criterio

        } else if (criteriaType.equals("MIN_ABOVE_VALUE")) {
            // Si es MIN_ABOVE_VALUE, tomamos la nota mínima registrada
            double minGrade = Double.MAX_VALUE;
            for (String evaluationName : evaluationsUsed) {
                double grade = grades.get(evaluationName);
                if (grade < minGrade) {
                    minGrade = grade;
                }
            }
            return minGrade >= criteriaValue; // El estudiante aprueba si la nota mínima es mayor o igual al valor del criterio

        } else {
            // Si el tipo de criterio es desconocido, lo consideramos como no aprobado
            return false;
        }
    }

    // Obtener las evaluaciones que el estudiante tiene registradas
    public List<String> getEvaluationsUsed(ApprovalCriteria criteria, Map<String, Double> grades) {
        List<String> evaluationsUsed = new ArrayList<>();
        List<String> requiredEvaluations = criteria.getEvaluationNames(); // Evaluaciones necesarias según los criterios

        // Filtrar las evaluaciones que se tienen registradas para el estudiante
        for (String evaluation : requiredEvaluations) {
            if (grades.containsKey(evaluation)) {
                evaluationsUsed.add(evaluation);
            } else {
                System.out.println("Advertencia: Evaluación no registrada " + evaluation);
            }
        }

        return evaluationsUsed;
    }
}
