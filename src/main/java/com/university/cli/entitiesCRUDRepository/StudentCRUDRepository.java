package com.university.cli.entitiesCRUDRepository;

import com.university.cli.interfaces.CRUDRepository;
import com.university.entities.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentCRUDRepository implements CRUDRepository<Student> {
    private final Map<Integer, Student> studentDatabase = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(Student student) {
        student.setId(currentId++);
        studentDatabase.put(student.getId(), student);
        System.out.println("Estudiante creado: " + student);
    }

    @Override
    public Student read(int id) {
        return studentDatabase.get(id);
    }

    @Override
    public void update(int id, Student student) {
        if (studentDatabase.containsKey(id)) {
            student.setId(id);
            studentDatabase.put(id, student);
            System.out.println("Estudiante actualizado: " + student);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    @Override
    public void delete(int id) {
        if (studentDatabase.remove(id) != null) {
            System.out.println("Estudiante eliminado.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
