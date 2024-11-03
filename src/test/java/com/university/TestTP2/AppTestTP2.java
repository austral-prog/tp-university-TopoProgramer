package com.university.TestTP2;

import com.university.App.App;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppTestTP2 {

    @Test
    public void testMain() {
        // Verificar que el método main se ejecuta sin excepciones
        assertDoesNotThrow(() -> App.main(new String[]{}));

        // Verificar que se ha creado el archivo solutions.csv
        assertTrue(Files.exists(Paths.get("src/main/resources/solution2.csv")), "El archivo solutions.csv no fue creado.");
    }
}