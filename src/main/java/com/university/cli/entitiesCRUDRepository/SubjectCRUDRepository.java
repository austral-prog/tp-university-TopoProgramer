package com.university.cli.entitiesCRUDRepository;

import com.university.cli.interfaces.CRUDRepository;
import com.university.entities.Subject;

import java.util.HashMap;
import java.util.Map;

public class SubjectCRUDRepository implements CRUDRepository<Subject> {
    private final Map<Integer, Subject> subjectDatabase = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(Subject subject) {
        subject.setId(currentId++);
        subjectDatabase.put(subject.getId(), subject);
        System.out.println("Materia creada: " + subject);
    }

    @Override
    public Subject read(int id) {
        return subjectDatabase.get(id);
    }

    @Override
    public void update(int id, Subject subject) {
        if (subjectDatabase.containsKey(id)) {
            subject.setId(id);
            subjectDatabase.put(id, subject);
            System.out.println("Materia actualizada: " + subject);
        } else {
            System.out.println("Materia no encontrada.");
        }
    }

    @Override
    public void delete(int id) {
        if (subjectDatabase.remove(id) != null) {
            System.out.println("Materia eliminada.");
        } else {
            System.out.println("Materia no encontrada.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Subject";
    }

    @Override
    public Class<Subject> getEntityClass() {
        return Subject.class;
    }
}
