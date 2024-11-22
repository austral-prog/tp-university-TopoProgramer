package com.university.tp2.csvActions;

import com.university.entities.Evaluation;
import com.university.interfaces.CsvWrite;
import com.university.tp2.typesOfExams.*;
import com.university.tp2.examManager.GradeCalculator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CsvWriteable_tp2 implements CsvWrite<List<StudentEvaluationDetails>> {

    private final GradeCalculator calculator = new GradeCalculator();
    private final SolutionProcessor processor = new SolutionProcessor();

    /**
     * Escribe datos de evaluaciones de estudiantes en un archivo CSV.
     *
     * @param filePath la ruta del archivo CSV a escribir
     * @param data la lista de detalles de evaluaciones de estudiantes
     * @throws IOException si ocurre un error de escritura
     */
    @Override
    public void write(String filePath, List<StudentEvaluationDetails> data) throws IOException {
        Map<String, List<Double>> gradesMap = new HashMap<>();
        Map<String, String> studentDetails = new HashMap<>();

        // Procesar datos de entrada
        for (StudentEvaluationDetails detail : data) {
            String key = detail.getSubject() + "," + detail.getEvaluationName() + "," + detail.getStudentName();
            studentDetails.putIfAbsent(key, detail.getEvaluationType());

            gradesMap.computeIfAbsent(key, k -> new ArrayList<>()).add(detail.getGrade());
        }

        // Crear resultados procesados
        List<StudentEvaluationDetails> results = gradesMap.entrySet().stream().map(entry -> {
            String[] keyParts = entry.getKey().split(",");
            String subject = keyParts[0];
            String evalName = keyParts[1];
            String studentName = keyParts[2];
            String evalType = studentDetails.get(entry.getKey());

            Evaluation eval = switch (evalType) {
                case "FINAL_PRACTICAL_WORK" -> new FinalExam(evalName);
                case "PRACTICAL_WORK" -> new PracticalWork(evalName);
                case "ORAL_EXAM" -> new OralExam(evalName);
                case "WRITTEN_EXAM" -> new WrittenExam(evalName);
                default -> throw new IllegalArgumentException("Unknown evaluation type: " + evalType);
            };

            double grade = calculator.calculateGrade(entry.getValue(), eval);
            return new StudentEvaluationDetails(studentName, subject, evalType, evalName, grade);
        }).collect(Collectors.toList());

        // Usar solutionProcessor para generar el archivo CSV ordenado
        processor.generateSolution(results, filePath);
    }
}
