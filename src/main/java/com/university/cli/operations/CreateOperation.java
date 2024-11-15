package com.university.cli.operations;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;
import com.university.entities.Classroom;
import com.university.entities.Student;
import com.university.entities.Teacher;
import com.university.entities.Subject;

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
                System.out.print("Nombre del profesor asignado: ");
                Teacher assignedTeacher = new Teacher();
                assignedTeacher.setName(scanner.nextLine());
                subject.setTeacher(assignedTeacher);
            }
            case Classroom classroom -> {
                System.out.print("Nombre de la clase (ej. 3B): ");
                classroom.setClassName(scanner.nextLine());
                System.out.print("Nombre del sujeto: ");
                Subject subject = new Subject();
                subject.setSubjectName(scanner.nextLine());
                classroom.setSubject(subject);
                System.out.print("Nombre del profesor: ");
                Teacher teacher = new Teacher();
                teacher.setName(scanner.nextLine());
                classroom.setTeacher(teacher);
            }
            case null, default -> System.out.println("Entidad no reconocida.");
        }
    }
}
