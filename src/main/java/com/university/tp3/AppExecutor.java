package com.university.tp3;

import com.university.tp3.csvActions.CsvReader_tp3;
import com.university.tp3.csvActions.CsvWriter_tp3;

import java.io.IOException;
import java.util.List;

public class AppExecutor {

    private final CsvReader_tp3 csvReader;
    private final CsvWriter_tp3 csvWriter;

    public AppExecutor() {
        this.csvReader = new CsvReader_tp3();
        this.csvWriter = new CsvWriter_tp3();
    }

    public void execute(String inputCriteriaPath, String solution2Path, String expectedOutputPath) {
        try {
            // Leer criterios de aprobación desde input_3.csv
            List<ApprovalCriteria> criteriaList = csvReader.read(inputCriteriaPath);

            // Evaluar los estudiantes y escribir los resultados en expected_3.csv
            csvWriter.evaluateAndWrite(solution2Path, expectedOutputPath, criteriaList);

            System.out.println("El archivo expected_3.csv se ha generado correctamente.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al procesar los archivos: " + e.getMessage());
        }
    }
}
