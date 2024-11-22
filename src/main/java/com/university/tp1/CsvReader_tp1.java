package com.university.tp1;

import com.university.interfaces.CsvRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReader_tp1 implements CsvRead<Map<String, Integer>> {

    private final Map<String, Set<String>> studentSubjectMap;

    public CsvReader_tp1() {
        this.studentSubjectMap = new HashMap<>();
    }

    /**
     * Lee datos desde un archivo CSV y los procesa.
     *
     * @param filePath la ruta del archivo CSV a leer
     * @throws IOException si ocurre un error de lectura
     */
    @Override
    public void read(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar el encabezado del CSV

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String subjectName = values[1]; // Nombre de la materia
                String studentName = values[2]; // Nombre del estudiante

                // Añadir la materia al conjunto de materias del estudiante
                studentSubjectMap.putIfAbsent(studentName, new HashSet<>());
                studentSubjectMap.get(studentName).add(subjectName);
            }
        }
    }

    /**
     * Obtiene el conteo de materias por estudiante.
     *
     * @return un mapa que asocia el nombre del estudiante con el número de materias
     */
    public Map<String, Integer> getStudentSubjectCount() {
        Map<String, Integer> studentSubjectCount = new HashMap<>();

        // Contar las materias distintas para cada estudiante
        for (Map.Entry<String, Set<String>> entry : studentSubjectMap.entrySet()) {
            studentSubjectCount.put(entry.getKey(), entry.getValue().size());
        }

        return studentSubjectCount;
    }
}
