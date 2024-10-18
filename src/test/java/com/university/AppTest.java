package com.university;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testMain() {
        // Verificar que el método main se ejecuta sin excepciones
        assertDoesNotThrow(() -> App.main(new String[]{}));

        // Verificar que se ha creado el archivo solutions.csv
        assertTrue(Files.exists(Paths.get("C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\solutions.csv")), "El archivo solutions.csv no fue creado.");
    }
}
