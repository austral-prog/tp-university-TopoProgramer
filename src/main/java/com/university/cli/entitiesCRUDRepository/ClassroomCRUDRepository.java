package com.university.cli.entitiesCRUDRepository;

import com.university.cli.interfaces.CRUDRepository;
import com.university.entities.Classroom;

import java.util.HashMap;
import java.util.Map;

public class ClassroomCRUDRepository implements CRUDRepository<Classroom> {
    private final Map<Integer, Classroom> classroomDatabase = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(Classroom classroom) {
        classroom.setId(currentId++);
        classroomDatabase.put(classroom.getId(), classroom);
        System.out.println("Aula creada: " + classroom);
    }

    @Override
    public Classroom read(int id) {
        return classroomDatabase.get(id);
    }

    @Override
    public void update(int id, Classroom classroom) {
        if (classroomDatabase.containsKey(id)) {
            classroom.setId(id);
            classroomDatabase.put(id, classroom);
            System.out.println("Aula actualizada: " + classroom);
        } else {
            System.out.println("Aula no encontrada.");
        }
    }

    @Override
    public void delete(int id) {
        if (classroomDatabase.remove(id) != null) {
            System.out.println("Aula eliminada.");
        } else {
            System.out.println("Aula no encontrada.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Classroom";
    }

    @Override
    public Class<Classroom> getEntityClass() {
        return Classroom.class;
    }
}
