package com.university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {
    private Students student;

    @BeforeEach
    public void setUp() {
        // Inicializamos un objeto de Students antes de cada test
        student = new Students("Classroom A", "Mathematics", "Alice Azure", "alice@example.com", "Prof. Smith");
    }

    @Test
    public void testGetters() {
        // Verificamos que los getters devuelvan los valores correctos
        assertEquals("Classroom A", student.getClassroom(), "Classroom should be 'Classroom A'");
        assertEquals("Mathematics", student.getSubject(), "Subject should be 'Mathematics'");
        assertEquals("Alice Azure", student.getStudentName(), "Student name should be 'Alice Azure'");
        assertEquals("alice@example.com", student.getStudentEmail(), "Email should be 'alice@example.com'");
        assertEquals("Prof. Smith", student.getSubjectTeacher(), "Teacher should be 'Prof. Smith'");
    }

    @Test
    public void testSetters() {
        // Probamos que los setters cambien correctamente los valores
        student.setClassroom("Classroom B");
        assertEquals("Classroom B", student.getClassroom(), "Classroom should be updated to 'Classroom B'");

        student.setSubject("Physics");
        assertEquals("Physics", student.getSubject(), "Subject should be updated to 'Physics'");

        student.setStudentName("Bob Brown");
        assertEquals("Bob Brown", student.getStudentName(), "Student name should be updated to 'Bob Brown'");

        student.setStudentEmail("bob@example.com");
        assertEquals("bob@example.com", student.getStudentEmail(), "Email should be updated to 'bob@example.com'");

        student.setSubjectTeacher("Prof. Jones");
        assertEquals("Prof. Jones", student.getSubjectTeacher(), "Teacher should be updated to 'Prof. Jones'");
    }

    @Test
    public void testToString() {
        // Comprobamos que el método toString devuelva el formato esperado
        String expected = "Students{classroom='Classroom A', subject='Mathematics', studentName='Alice Azure', studentEmail='alice@example.com', subjectTeacher='Prof. Smith'}";
        assertEquals(expected, student.toString(), "toString should return the correct format");
    }
}
