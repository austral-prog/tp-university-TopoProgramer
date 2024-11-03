package com.university.Utils.TP1; // Declaración del paquete de la clase

import java.util.List; // Importa List para listas generales
import java.util.Map;


public interface csvRead {
    Map<String, List<String>> readCSVAsMap(String filePath); // Método para leer CSV
}