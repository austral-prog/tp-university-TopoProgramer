package com.university.tp2.csvActions;

import java.io.IOException;
import java.util.List;

public class CsvExtract {

    public void processCsv(String inputFilePath, String outputFilePath) {
        // Crear una instancia de CsvReader_tp2
        CsvReader_tp2 csvReader = new CsvReader_tp2();

        try {
            // Leer los detalles de evaluación desde el archivo input_2.csv
            csvReader.read(inputFilePath);  // No se asigna nada aquí, solo lee el archivo

            // Obtener los detalles de evaluación desde el lector
            List<StudentEvaluationDetails> data = csvReader.getStudentEvaluationDetails();
            System.out.println("Datos leídos correctamente del archivo.");

            // Crear la instancia de CsvWriteable_tp2 y procesar los datos
            CsvWriteable_tp2 csvWriter = new CsvWriteable_tp2();
            // Llamar al método para escribir el archivo CSV ordenado
            csvWriter.write(outputFilePath, data);

            System.out.println("Archivo CSV generado correctamente en: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al procesar los archivos.");
        }
    }
}
