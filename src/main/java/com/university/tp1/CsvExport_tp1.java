package com.university.tp1;

import java.io.IOException;
import java.util.*;

public class CsvExport_tp1 {

    private CsvReader_tp1 csvReaderTp1;
    private CsvWriter_tp1 csvWriterTp1;

    // Constructor que acepta CsvReader_tp1 y CsvWriter_tp1
    public CsvExport_tp1(CsvReader_tp1 csvReaderTp1, CsvWriter_tp1 csvWriterTp1) {
        this.csvReaderTp1 = csvReaderTp1;
        this.csvWriterTp1 = csvWriterTp1;
    }

    // Método para gestionar el proceso de exportación
    public void export(String inputFilePath, String outputFilePath) {
        try {
            // Leer el CSV y obtener los datos
            csvReaderTp1.read(inputFilePath);
            Map<String, Integer> studentSubjectCount = csvReaderTp1.getStudentSubjectCount();

            // Ordenar los datos por el nombre del estudiante
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(studentSubjectCount.entrySet());
            sortedEntries.sort(Map.Entry.comparingByKey());

            // Crear un nuevo mapa ordenado para pasar al CsvWriter_tp1
            Map<String, Integer> sortedStudentSubjectCount = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                sortedStudentSubjectCount.put(entry.getKey(), entry.getValue());
            }

            // Escribir los resultados ordenados en el archivo CSV de salida
            csvWriterTp1.write(outputFilePath, sortedStudentSubjectCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
