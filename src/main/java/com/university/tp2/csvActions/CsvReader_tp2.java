package com.university.tp2.csvActions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader_tp2 {

    private final List<StudentEvaluationDetails> studentEvaluationDetailsList = new ArrayList<>();

    // Método para leer el archivo CSV
    public void read(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            // Saltar la cabecera del archivo CSV (si existe)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Asumimos que los campos están separados por comas
                String[] fields = line.split(",");

                // Crear una nueva instancia de StudentEvaluationDetails usando los datos del archivo
                String studentName = fields[0];
                String subject = fields[1];
                String evaluationType = fields[2];
                String evaluationName = fields[3];
                String exercise_Name = fields[4];
                double grade = Double.parseDouble(fields[5]);

                // Crear un objeto StudentEvaluationDetails y agregarlo a la lista
                StudentEvaluationDetails details = new StudentEvaluationDetails(studentName,subject, evaluationType,evaluationName,grade);
                studentEvaluationDetailsList.add(details);
            }
        }
    }

    // Método para obtener los detalles leídos
    public List<StudentEvaluationDetails> getStudentEvaluationDetails() {
        return studentEvaluationDetailsList;
    }
}
