package com.university.TestTP2;


import com.university.Utils.TP2.CSVActions.csvWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.university.Data.result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class csvWriterTest {

    private csvWriter writer;
    private String outputFilePath;

    @BeforeEach
    public void setUp() {
        writer = new csvWriter();
        outputFilePath = "src/test/resources/output_test.csv";
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Eliminar archivo de prueba después del test
        Files.deleteIfExists(Paths.get(outputFilePath));
    }

    @Test
    public void testWriteResultsToCSV() throws IOException {
        List<result> results = new ArrayList<>();
        results.add(new result("Math", "Final Exam", "Alice Azure", 85.0));
        results.add(new result("Science", "Oral Exam", "Bob Brown", 90.0));

        // Escribimos los resultados en el archivo de salida
        writer.writeResultsToCSV(results, outputFilePath);

        // Verificamos que el archivo se haya escrito correctamente
        List<String> outputLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(outputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                outputLines.add(line);
            }
        }

        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", outputLines.get(0));
        assertEquals("Math,Final Exam,Alice Azure,85.0", outputLines.get(1));
        assertEquals("Science,Oral Exam,Bob Brown,90.0", outputLines.get(2));
    }
}
