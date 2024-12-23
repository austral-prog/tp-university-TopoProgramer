package com.university.tp3.csvActions;

import com.university.tp3.ApprovalCriteria;
import com.university.tp3.CriteriaEvaluator;
import java.io.*;
import java.util.*;

public class CsvWriter_tp3 {
    private final CriteriaEvaluator calculator = new CriteriaEvaluator(); // Calculador de criterios

    /**
     * Evalúa y escribe los resultados de los estudiantes en un archivo CSV.
     *
     * @param solution2Path la ruta del archivo CSV con las notas de los estudiantes
     * @param expectedOutputPath la ruta del archivo CSV donde se escribirán los resultados
     * @param criteriaList la lista de criterios de aprobación
     * @throws IOException si ocurre un error de lectura o escritura
     */
    public void evaluateAndWrite(String solution2Path, String expectedOutputPath, List<ApprovalCriteria> criteriaList) throws IOException {
        Map<String, Map<String, Double>> studentGrades = new HashMap<>();

        // Leer solution2.csv y cargar las notas de los estudiantes
        try (BufferedReader br = new BufferedReader(new FileReader(solution2Path))) {
            String line;
            br.readLine();  // Saltar la cabecera
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String subject = fields[0];
                String evaluationName = fields[1];
                String studentName = fields[2];
                double grade = Double.parseDouble(fields[3]);

                String key = subject + "," + studentName;
                studentGrades.putIfAbsent(key, new HashMap<>());
                studentGrades.get(key).put(evaluationName, grade);
            }
        }

        // Ordenar los criterios por materia
        criteriaList.sort(Comparator.comparing(ApprovalCriteria::getSubject));

        // Escribir los resultados en expected_3.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expectedOutputPath))) {
            writer.write("Subject_Name,Student_Name,Approve\n");

            // Recorrer los criterios ordenados
            for (ApprovalCriteria criteria : criteriaList) {
                String subject = criteria.getSubject();

                // Filtrar y ordenar los estudiantes por nombre
                studentGrades.entrySet().stream()
                        .filter(entry -> entry.getKey().startsWith(subject))
                        .sorted(Comparator.comparing(entry -> entry.getKey().split(",")[1])) // Ordenar por nombre del estudiante
                        .forEach(entry -> {
                            String studentName = entry.getKey().split(",")[1];
                            Map<String, Double> grades = entry.getValue();
                            boolean approved = calculator.isApproved(criteria, grades);

                            // Escribir resultado
                            writeResult(writer, subject, studentName, approved);
                        });
            }
        }
    }

    /**
     * Escribe un resultado en el archivo CSV.
     *
     * @param writer el BufferedWriter para escribir en el archivo
     * @param subject el nombre de la materia
     * @param studentName el nombre del estudiante
     * @param approved true si el estudiante aprueba, false en caso contrario
     */
    private void writeResult(BufferedWriter writer, String subject, String studentName, boolean approved) {
        try {
            writer.write(String.format("%s,%s,%s\n", subject, studentName, approved ? "YES" : "NO"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
