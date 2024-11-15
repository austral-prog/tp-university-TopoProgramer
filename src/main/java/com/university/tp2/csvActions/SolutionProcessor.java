package com.university.tp2.csvActions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SolutionProcessor {
    public void generateSolution(List<StudentEvaluationDetails> results, String outputPath) {
        // Ordenar los resultados por el nombre de la materia
        results.sort(Comparator.comparing(StudentEvaluationDetails::getSubject)
                .thenComparing(StudentEvaluationDetails::getEvaluationName)
                .thenComparing(StudentEvaluationDetails::getStudentName));

        // Escribir los resultados ordenados en un archivo CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");
            for (StudentEvaluationDetails result : results) {
                String line = String.format("%s,%s,%s,%.2f\n",
                        result.getSubject(),
                        result.getEvaluationName(),
                        result.getStudentName(),
                        result.getGrade());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
