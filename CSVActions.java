package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class CSVActions {

    // Método para leer un archivo CSV y devolver un Map con los nombres de las columnas como claves y las filas como listas de valores
    public Map<String, List<String>> readCSVAsMap(String filePath) {
        Map<String, List<String>> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(",");

                for (String header : headers) {
                    data.put(header, new ArrayList<>());
                }

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    for (int i = 0; i < headers.length; i++) {
                        String header = headers[i];
                        String value = i < values.length ? values[i] : "";
                        data.get(header).add(value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    // Método para escribir un Map con los datos en un archivo CSV
    public void writeCSVFromMap(Map<String, Integer> data, String outputFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Escribir el header
            bw.write("Student_Name,Course_Count\n");

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String studentName = entry.getKey();
                Integer courseCount = entry.getValue();

                // Escribir la línea con el nombre del estudiante y la cantidad de cursos
                bw.write(studentName + "," + courseCount + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Método para agrupar estudiantes por materias y contar cuántas materias distintas toma cada uno
    public Map<String, Integer> aggregateByStudent(Map<String, List<String>> data) {
        Map<String, Integer> studentCourseCount = new HashMap<>();
        List<String> studentNames = data.get("Student_Name");
        List<String> subjects = data.get("Subject");

        Map<String, Set<String>> studentSubjects = new HashMap<>();

        for (int i = 0; i < studentNames.size(); i++) {
            String studentName = studentNames.get(i);
            String subject = subjects.get(i);

            // Agregar el curso a un conjunto para evitar duplicados
            studentSubjects.putIfAbsent(studentName, new HashSet<>());
            studentSubjects.get(studentName).add(subject);
        }

        for (String student : studentSubjects.keySet()) {
            studentCourseCount.put(student, studentSubjects.get(student).size());
        }

        return studentCourseCount;
    }



    public void processCSV(String inputFilePath, String outputFilePath) {
        // Leer los datos del CSV como un mapa
        Map<String, List<String>> data = readCSVAsMap(inputFilePath);

        // Agregar estudiantes por materia y contar cuántas materias distintas toma cada uno
        Map<String, Integer> studentCourseCount = aggregateByStudent(data);

        // Ordenar el mapa por clave (nombre del estudiante)
        Map<String, Integer> sortedStudentCourseCount = new TreeMap<>(studentCourseCount);

        // Escribir el resultado en el archivo de salida
        writeCSVFromMap(sortedStudentCourseCount, outputFilePath);
    }





    private List<Students> readStudentsFromCSV(String filePath) {
        List<Students> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String headerLine = br.readLine(); // Leer la línea del encabezado
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Suponiendo que el CSV está separado por comas
                if (values.length >= 5) { // Asegúrate de que hay suficientes valores
                    String classroom = values[0].trim();
                    String subject = values[1].trim();
                    String studentName = values[2].trim();
                    String studentEmail = values[3].trim();
                    String subjectTeacher = values[4].trim();

                    Students student = new Students(classroom, subject, studentName, studentEmail, subjectTeacher);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void writeStudentsToCSV(List<Students> students, String outputFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Escribir el header
            bw.write("Classroom,Subject,Student_Name,Student_Email,Subject_Teacher\n");

            for (Students student : students) {
                bw.write(String.join(",", student.getClassroom(), student.getSubject(), student.getStudentName(), student.getStudentEmail(), student.getSubjectTeacher()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
