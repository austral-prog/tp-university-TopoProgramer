package com.university.TestTP2;

import com.university.Utils.TP2.CSVActions.csvReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.university.student;
import org.junit.jupiter.api.AfterEach;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class csvReaderTest {

    private csvReader reader;
    private String inputFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        reader = new csvReader();
        inputFilePath = "src/test/resources/input_test.csv";

        // Crear un archivo CSV de ejemplo para el test
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFilePath))) {
            writer.write("Student_Name,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade\n");
            writer.write("Alice Azure,Math,Final Exam,Exam 1,Exercise 1,85.0\n");
            writer.write("Bob Brown,Science,Oral Exam,Exam 2,Exercise 2,90.0\n");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Eliminar archivo de prueba después del test
        Files.deleteIfExists(Paths.get(inputFilePath));
    }

    @Test
    public void testReadStudentsFromCSV() {
        List<student> students = reader.readStudentsFromCSV(inputFilePath);

        // Verificar que se hayan leído correctamente los datos
        assertEquals(2, students.size());

        student student1 = students.get(0);
        assertEquals("Alice Azure", student1.getStudentName());
        assertEquals("Math", student1.getSubject());
        assertEquals("Final Exam", student1.getEvaluationType());
        assertEquals("Exam 1", student1.getEvaluationName());
        assertEquals("Exercise 1", student1.getExerciseName());
        assertEquals(85.0, student1.getGrade());

        student student2 = students.get(1);
        assertEquals("Bob Brown", student2.getStudentName());
        assertEquals("Science", student2.getSubject());
        assertEquals("Oral Exam", student2.getEvaluationType());
        assertEquals("Exam 2", student2.getEvaluationName());
        assertEquals("Exercise 2", student2.getExerciseName());
        assertEquals(90.0, student2.getGrade());
    }
}
