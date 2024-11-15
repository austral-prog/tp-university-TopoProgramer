package com.university.entities;

import com.university.cli.interfaces.Entity;

public class Teacher implements Entity {
    private String name;
    private int id;

    // Constructor sin argumentos
    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    // Getter y Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Materia ID: " + id + " | Nombre del profesor: " + getName();
    }
}
