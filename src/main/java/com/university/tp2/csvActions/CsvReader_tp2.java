package com.university.tp2.csvActions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader_tp2 {

    private final List<StudentEvaluationDetails> studentEvaluationDetailsList = new ArrayList<>();

    /**
     * Lee un archivo CSV y almacena los detalles de las evaluaciones de estudiantes.
     *
     * @param inputFilePath la ruta del archivo CSV a leer
     * @throws IOException si ocurre un error de lectura
     */
    public void read(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            // Saltar la cabecera del archivo CSV (si existe)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Asumimos que los campos están separados por comas
                String[] fields = line.split(",");

                // Crear una nueva instancia de StudentEvaluationDetails usando los datos del archivo
                StudentEvaluationDetails details = getDetails(fields);
                studentEvaluationDetailsList.add(details);
            }
        }
    }

    private static StudentEvaluationDetails getDetails(String[] fields) {
        String studentName = fields[0];
        String subject = fields[1];
        String evaluationType = fields[2];
        String evaluationName = fields[3];
        double grade = Double.parseDouble(fields[5]);

        // Crear un objeto StudentEvaluationDetails y agregarlo a la lista
        return new StudentEvaluationDetails(studentName, subject, evaluationType, evaluationName, grade);
    }

    /**
     * Obtiene los detalles de las evaluaciones leídas desde el archivo CSV.
     *
     * @return una lista de objetos StudentEvaluationDetails
     */
    public List<StudentEvaluationDetails> getStudentEvaluationDetails() {
        return studentEvaluationDetailsList;
    }
}
