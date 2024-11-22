package com.university.entities;

import com.university.cli.interfaces.Entity;

import java.util.ArrayList;
import java.util.List;

public class Classroom implements Entity {
    private int id;
    private String className;
    private Subject subject;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();

    /**
     *
     * @param className el nombre de la clase
     * @param subject la materia de la clase
     * @param teacher teacher el profesor de la clase
     * @param students la lista de estudiantes en la clase
     */

    // Constructor
    public Classroom(String className, Subject subject, Teacher teacher, List<Student> students) {
        this.className = className;
        this.subject = subject;
        this.teacher = teacher;
        this.students = students;
    }

    public Classroom() {
    }

    // Getters y Setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append(" | Subject: ").append(subject != null ? subject.getSubjectName() : "N/A")
                .append(" | Profesor: ").append(teacher != null ? teacher.getName() : "N/A")
                .append(" | Clase: ").append(className)
                .append("\nEstudiantes:\n");

        for (Student student : students) {
            sb.append("- ").append(student.getName())
                    .append(" (").append(student.getEmail()).append(")\n");
        }
        return sb.toString();
    }

}
