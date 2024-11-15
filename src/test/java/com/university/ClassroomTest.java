package com.university;

import com.university.entities.Classroom;
import com.university.entities.Student;
import com.university.entities.Subject;
import com.university.entities.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassroomTest {

    private Classroom classroom;
    private Teacher teacher;
    private Subject subject;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        // Crear instancias de ejemplo
        teacher = new Teacher("Prof. Smith");
        teacher.setId(1);

        subject = new Subject("Matemáticas", teacher);
        subject.setId(101);

        Student student1 = new Student("John Doe", "john.doe@example.com");
        student1.setId(1001);
        Student student2 = new Student("Jane Roe", "jane.roe@example.com");
        student2.setId(1002);

        students = Arrays.asList(student1, student2);

        // Crear instancia de Classroom
        classroom = new Classroom("3A", subject, teacher, students);
        classroom.setId(10);
    }

    @Test
    public void testClassroomToString() {
        String expectedOutput = "ID: 10 | Subject: Matemáticas | Profesor: Prof. Smith | Clase: 3A\n" +
                "Estudiantes:\n" +
                "- John Doe (john.doe@example.com)\n" +
                "- Jane Roe (jane.roe@example.com)\n";
        assertEquals(expectedOutput, classroom.toString());
    }

    @Test
    public void testGettersAndSetters() {
        // Verificar getters
        assertEquals("3A", classroom.getClassName());
        assertEquals(subject, classroom.getSubject());
        assertEquals(teacher, classroom.getTeacher());
        assertEquals(students, classroom.getStudents());

        // Verificar setters
        Teacher newTeacher = new Teacher("Prof. Johnson");
        newTeacher.setId(2);
        classroom.setTeacher(newTeacher);
        assertEquals(newTeacher, classroom.getTeacher());
    }
}
