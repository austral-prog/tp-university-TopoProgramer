package com.university.Utils.TP2.CSVActions; // Declaración del paquete de la clase

import com.university.student; // Importa la clase Students

import java.util.List; // Importa List para listas generales

// Interfaz que define los métodos de lectura y escritura de CSV
public interface csvRead {
    List<student> readStudentsFromCSV(String filePath); // Método para leer CSV
}