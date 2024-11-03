package com.university.Utils.TP2;

import com.university.Data.*;
import com.university.Data.TypeOfExams.finalExam;
import com.university.Data.TypeOfExams.oralExam;
import com.university.Data.TypeOfExams.practicalWork;
import com.university.Data.TypeOfExams.writtenExam;
import com.university.student;
import com.university.Utils.TP2.CSVActions.csvReader;
import com.university.Utils.TP2.CSVActions.csvWriter;

import java.util.*;
import java.util.stream.Collectors;

public class examProcessor {

    private final csvReader reader;
    private final csvWriter writer;
    private final gradeCalculator calculator;
    private final com.university.Utils.TP2.solutionProcessor solutionProcessor; // Nueva instancia de solutionProcessor
    private List<result> results; // Agregamos este atributo


    public examProcessor(csvReader reader, csvWriter writer, gradeCalculator calculator, solutionProcessor solutionProcessor) {
        this.reader = reader;
        this.writer = writer;
        this.calculator = calculator;
        this.solutionProcessor = solutionProcessor; // Inicialización
        this.results = new ArrayList<>();

    }

    public void processExams(String inputFilePath, String outputFilePath) {
        List<student> students = reader.readStudentsFromCSV(inputFilePath);
        Map<String, List<Double>> gradesMap = new HashMap<>();
        Map<String, String> studentDetails = new HashMap<>();

        for (student st : students) {
            String key = st.getSubject() + "," + st.getEvaluationName() + "," + st.getStudentName();
            studentDetails.putIfAbsent(key, st.getEvaluationType());

            gradesMap.computeIfAbsent(key, k -> new ArrayList<>()).add(st.getGrade());
        }

        List<result> results = gradesMap.entrySet().stream().map(entry -> {
            String[] keyParts = entry.getKey().split(",");
            String subject = keyParts[0];
            String evalName = keyParts[1];
            String studentName = keyParts[2];
            String evalType = studentDetails.get(entry.getKey());

            evaluation eval = switch (evalType) {
                case "FINAL_PRACTICAL_WORK" -> new finalExam(evalName);
                case "PRACTICAL_WORK" -> new practicalWork(evalName);
                case "ORAL_EXAM" -> new oralExam(evalName);
                case "WRITTEN_EXAM" -> new writtenExam(evalName);
                default -> throw new IllegalArgumentException("Unknown evaluation type: " + evalType);
            };

            double grade = calculator.calculateGrade(entry.getValue(), eval);
            return new result(subject, evalName, studentName, grade);
        }).collect(Collectors.toList());

        // Llamar al solutionProcessor para generar el archivo CSV ordenado
        solutionProcessor.generateSolution(results, outputFilePath);

    }
    public List<result> getResults() {
        return results;
    }
}
