package com.university.cli.entitiesCRUDRepository;

import com.university.cli.interfaces.CRUDRepository;
import com.university.entities.Teacher;

import java.util.HashMap;
import java.util.Map;

public class TeacherCRUDRepository implements CRUDRepository<Teacher> {
    private final Map<Integer, Teacher> teacherDatabase = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(Teacher teacher) {
        teacher.setId(currentId++);
        teacherDatabase.put(teacher.getId(), teacher);
        System.out.println("Profesor creado: " + teacher);
    }

    @Override
    public Teacher read(int id) {
        return teacherDatabase.get(id);
    }

    @Override
    public void update(int id, Teacher teacher) {
        if (teacherDatabase.containsKey(id)) {
            teacher.setId(id);
            teacherDatabase.put(id, teacher);
            System.out.println("Profesor actualizado: " + teacher);
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    @Override
    public void delete(int id) {
        if (teacherDatabase.remove(id) != null) {
            System.out.println("Profesor eliminado.");
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    @Override
    public String getIdentifier() {
        return "Teacher";
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}
