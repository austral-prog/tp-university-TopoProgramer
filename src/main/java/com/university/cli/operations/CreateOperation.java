package com.university.cli.operations;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;
import com.university.entities.Classroom;
import com.university.entities.Student;
import com.university.entities.Teacher;
import com.university.entities.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateOperation<T extends Entity> {
    private final Scanner scanner;

    public CreateOperation(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(CRUDRepository<T> crud) {
        try {
            T entity = crud.getEntityClass().getDeclaredConstructor().newInstance();
            fillEntity(entity);
            crud.create(entity);
            System.out.println("Entidad creada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear la entidad: " + e.getMessage());
        }
    }

    private void fillEntity(T entity) {
        switch (entity) {
            case Student student -> {
                System.out.print("Nombre del estudiante: ");
                student.setName(scanner.nextLine());

                System.out.print("Email del estudiante: ");
                student.setEmail(scanner.nextLine());
            }
            case Teacher teacher -> {
                System.out.print("Nombre del profesor: ");
                teacher.setName(scanner.nextLine());

                System.out.print("ID del profesor: ");
                teacher.setId(Integer.parseInt(scanner.nextLine()));
            }
            case Subject subject -> {
                System.out.print("Nombre de la materia: ");
                subject.setSubjectName(scanner.nextLine());

                Teacher teacher = new Teacher();

                System.out.print("Nombre del profesor: ");
                subject.setTeacher(teacher);

            }
            case Classroom classroom -> {
                List<Student> listOfStudents = new ArrayList<>();

                System.out.print("Nombre del estudiante: ");
                String studentName = scanner.nextLine();
                System.out.print("Email del estudiante: ");
                String studentEmail = scanner.nextLine();
                Student assignedStudent = new Student(studentName, studentEmail);
                listOfStudents.add(assignedStudent);

                System.out.print("Nombre del profesor asignado: ");
                String teacherName = scanner.nextLine();
                Teacher assignedTeacher = new Teacher(teacherName);
                classroom.setTeacher(assignedTeacher);

                System.out.print("Nombre de la materia: ");
                String subjectName = scanner.nextLine();
                Subject assignedSubject = new Subject(subjectName, assignedTeacher);
                classroom.setSubject(assignedSubject);

                System.out.print("Nombre de la clase (ej. 3B): ");
                String className = scanner.nextLine();
                classroom.setClassName(className);
                classroom.setStudents(listOfStudents); // Asigna la lista de estudiantes a la clase
            }
            case null, default -> System.out.println("Entidad no reconocida.");
        }
    }
}
