package com.university.entities;

import com.university.cli.interfaces.Entity;

public class Subject implements Entity {
    private String subjectName;
    private Teacher teacher;
    private int id;

    // Constructor
    public Subject() {
    }

    public Subject(String subjectName, Teacher teacher) {
        this.subjectName = subjectName;
        this.teacher = teacher;

    }

    // Getters y Setters
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Materia ID: " + id + " | Nombre: " + subjectName + " | Profesor: " + (teacher != null ? teacher.getName() : "N/A");
    }
}
