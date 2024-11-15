package com.university.cli.operations;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;
import com.university.entities.Classroom;
import com.university.entities.Student;
import com.university.entities.Subject;
import com.university.entities.Teacher;

import java.util.Scanner;

public class UpdateOperation<T extends Entity> {
    private final Scanner scanner;

    public UpdateOperation(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(CRUDRepository<T> crud) {
        System.out.print("Ingrese el ID de la entidad a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        T existingEntity = crud.read(id);
        if (existingEntity != null) {
            fillEntity(existingEntity);
            crud.update(id, existingEntity);
            System.out.println("Entidad actualizada con éxito.");
        } else {
            System.out.println("Entidad no encontrada.");
        }
    }

    private void fillEntity(T entity) {
        if (entity instanceof Student student) {
            System.out.print("Nombre del estudiante: ");
            student.setName(scanner.nextLine());
            System.out.print("Email del estudiante: ");
            student.setEmail(scanner.nextLine());
        } else if (entity instanceof Teacher teacher) {
            System.out.print("Nombre del profesor: ");
            teacher.setName(scanner.nextLine());
        } else if (entity instanceof Subject subject) {
            System.out.print("Nombre de la materia: ");
            subject.setSubjectName(scanner.nextLine());
        } else if (entity instanceof Classroom classroom) {
            System.out.print("Nombre de la clase (ej. 3B): ");
            classroom.setClassName(scanner.nextLine());
            System.out.print("Nombre del sujeto: ");
            classroom.getSubject().setSubjectName(scanner.nextLine());
            System.out.print("Nombre del profesor: ");
            classroom.getTeacher().setName(scanner.nextLine());
        } else {
            System.out.println("Entidad no reconocida.");
        }
    }
}
