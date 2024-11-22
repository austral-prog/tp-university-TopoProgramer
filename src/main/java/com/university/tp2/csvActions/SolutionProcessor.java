package com.university.tp2.csvActions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SolutionProcessor {

    /**
     * Genera un archivo CSV con los resultados ordenados de las evaluaciones de los estudiantes.
     *
     * @param results la lista de detalles de las evaluaciones de los estudiantes
     * @param outputPath la ruta del archivo CSV de salida
     */
    public void generateSolution(List<StudentEvaluationDetails> results, String outputPath) {
        // Ordenar los resultados por el nombre de la materia, evaluación y estudiante
        results.sort(Comparator.comparing(StudentEvaluationDetails::getSubject)
                .thenComparing(StudentEvaluationDetails::getEvaluationName)
                .thenComparing(StudentEvaluationDetails::getStudentName));

        // Escribir los resultados ordenados en un archivo CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n"); // Escribir encabezado
            for (StudentEvaluationDetails result : results) {
                String line = String.format("%s,%s,%s,%.2f\n",
                        result.getSubject(),
                        result.getEvaluationName(),
                        result.getStudentName(),
                        result.getGrade()); // Formatear cada línea
                writer.write(line); // Escribir línea en el archivo CSV
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
