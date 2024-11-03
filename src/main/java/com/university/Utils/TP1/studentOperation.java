package com.university.Utils.TP1;

import java.util.*;

public class studentOperation {

    // Método para contar la cantidad de cursos únicos tomados por cada estudiante
    public Map<String, Integer> aggregateByStudent(Map<String, List<String>> data) {
        Map<String, Integer> studentCourseCount = new HashMap<>();
        List<String> studentNames = data.get("Student_Name");
        List<String> subjects = data.get("Subject");

        // Mapear cada estudiante con los cursos únicos que toma
        Map<String, Set<String>> studentSubjects = new HashMap<>();

        for (int i = 0; i < studentNames.size(); i++) {
            String studentName = studentNames.get(i);
            String subject = subjects.get(i);

            // Usa un conjunto para evitar duplicados
            studentSubjects.putIfAbsent(studentName, new HashSet<>());
            studentSubjects.get(studentName).add(subject);
        }

        // Contar los cursos únicos por cada estudiante
        for (String student : studentSubjects.keySet()) {
            studentCourseCount.put(student, studentSubjects.get(student).size());
        }

        return studentCourseCount;
    }
}
