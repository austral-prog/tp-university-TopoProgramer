package com.university.TestTP1;


import com.university.Utils.TP1.csvTP1Writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertTrue;


import java.nio.file.Files;
import java.nio.file.Paths;


public class csvTP1WriterTest {

    private csvTP1Writer writer;

    @BeforeEach
    public void setUp() {
        writer = new csvTP1Writer();
    }

    @Test
    public void testWriteCSVFromMap() {
        Map<String, Integer> data = new HashMap<>();
        data.put("Alice Azure", 2);
        data.put("Bob Brown", 1);

        String outputFilePath = "src/test/resources/output_tp1.csv";
        writer.writeCSVFromMap(data, outputFilePath);

        // Verificamos que el archivo se ha creado
        assertTrue(Files.exists(Paths.get(outputFilePath)));
    }
}
