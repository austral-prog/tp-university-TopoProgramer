package com.university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVActionsTest {

    private CSVActions csvActions;

    @BeforeEach
    public void setUp() {
        csvActions = new CSVActions();
    }

    @Test
    public void testProcessCSV() throws IOException {
        String inputFilePath = "C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\input.csv";
        String outputFilePath = "C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\solutions.csv";
        String expectedFilePath = "C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\expected.csv";

        // Procesar el archivo CSV
        csvActions.processCSV(inputFilePath, outputFilePath);

        // Leer el archivo esperado
        List<String> expectedLines = Files.readAllLines(Paths.get(expectedFilePath));
        // Leer el archivo de salida
        List<String> outputLines = Files.readAllLines(Paths.get(outputFilePath));

        // Asegurarse de que el tamaño sea el mismo
        assertEquals(expectedLines.size(), outputLines.size(), "El número de líneas debe ser el mismo.");

        // Comparar cada línea del archivo de salida con el archivo esperado
        for (int i = 0; i < expectedLines.size(); i++) {
            assertEquals(expectedLines.get(i), outputLines.get(i), "La línea " + (i + 1) + " no coincide.");
        }

        // Limpiar el archivo de salida después de la prueba
        Files.deleteIfExists(Paths.get(outputFilePath));
    }
}
