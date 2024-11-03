package com.university.Utils.TP1;

import java.util.*;

public class csvAction {

    public void processCSV(String inputFilePath, String outputFilePath) {

        studentOperation studentCourseCount = new studentOperation();
        csvTP1Writer csvWrite = new csvTP1Writer();
        csvTP1Reader data = new csvTP1Reader();

        // Leer los datos del CSV como un mapa
        Map<String, List<String>> csvData = data.readCSVAsMap(inputFilePath);

        // Agregar estudiantes por materia y contar cuántas materias distintas toma cada uno
        Map<String, Integer> studentCourseCountMap = studentCourseCount.aggregateByStudent(csvData);

        // Ordenar el mapa por nombre completo (nombre y apellido)
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(studentCourseCountMap.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            // Ordenar alfabéticamente por nombre completo
            return entry1.getKey().compareToIgnoreCase(entry2.getKey());
        });

        // Convertir la lista ordenada a un LinkedHashMap para mantener el orden
        Map<String, Integer> sortedStudentCourseCount = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            sortedStudentCourseCount.put(entry.getKey(), entry.getValue());
        }

        // Escribir el resultado en el archivo de salida
        csvWrite.writeCSVFromMap(sortedStudentCourseCount, outputFilePath);
    }
}
