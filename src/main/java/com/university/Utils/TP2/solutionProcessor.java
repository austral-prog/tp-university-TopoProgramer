package com.university.Utils.TP2;

import com.university.Data.result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class solutionProcessor {
    public void generateSolution(List<result> results, String outputPath) {
        // Ordenar los resultados por el nombre de la materia
        Collections.sort(results, Comparator.comparing(result::getSubject));

        // Escribir los resultados ordenados en un archivo CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");
            for (result result : results) {
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
