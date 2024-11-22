package com.university.tp3;

import com.university.tp3.csvActions.CsvReader_tp3;
import com.university.tp3.csvActions.CsvWriter_tp3;
import java.io.IOException;
import java.util.List;

public class AppExecutor {

    private final CsvReader_tp3 csvReader; // Lector de archivos CSV
    private final CsvWriter_tp3 csvWriter; // Escritor de archivos CSV

    /**
     * Constructor por defecto que inicializa CsvReader_tp3 y CsvWriter_tp3.
     */
    public AppExecutor() {
        this.csvReader = new CsvReader_tp3();
        this.csvWriter = new CsvWriter_tp3();
    }

    /**
     * Ejecuta el proceso de evaluación y escritura de resultados.
     *
     * @param inputCriteriaPath la ruta del archivo CSV con los criterios de aprobación
     * @param solution2Path la ruta del archivo CSV con las notas de los estudiantes
     * @param expectedOutputPath la ruta del archivo CSV donde se escribirán los resultados
     */
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
