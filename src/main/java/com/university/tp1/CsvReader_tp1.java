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

    @Override
    public void read(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar el encabezado del CSV

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String classroom = values[0]; // El nombre del aula, no se usa en este caso
                String subjectName = values[1]; // Nombre de la materia
                String studentName = values[2]; // Nombre del estudiante
                String studentEmail = values[3]; // Correo del estudiante (no se usa en este caso)
                String teacherName = values[4]; // Nombre del profesor, no se usa en este caso

                // Añadir la materia al conjunto de materias del estudiante
                studentSubjectMap.putIfAbsent(studentName, new HashSet<>());
                studentSubjectMap.get(studentName).add(subjectName);
            }
        }
    }

    public Map<String, Integer> getStudentSubjectCount() {
        Map<String, Integer> studentSubjectCount = new HashMap<>();

        // Para cada estudiante, obtener la cantidad de materias distintas en las que está inscrito
        for (Map.Entry<String, Set<String>> entry : studentSubjectMap.entrySet()) {
            studentSubjectCount.put(entry.getKey(), entry.getValue().size());
        }

        return studentSubjectCount;
    }
}
