package com.university.testTP1;

import com.university.tp1.CsvExport_tp1;
import com.university.tp1.CsvReader_tp1;
import com.university.tp1.CsvWriter_tp1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvReaderWriterExportTests {

    private CsvReader_tp1 csvReader;
    private CsvWriter_tp1 csvWriter;
    private CsvExport_tp1 csvExport;

    @BeforeEach
    public void setUp() {
        csvReader = new CsvReader_tp1();
        csvWriter = new CsvWriter_tp1();
        csvExport = new CsvExport_tp1(csvReader, csvWriter);
    }

    @Test
    public void testCsvReader_readsCorrectly() throws IOException {
        // Ruta al archivo input.csv
        String inputFilePath = "src/main/resources/input.csv";

        // Ejecutar el método read
        csvReader.read(inputFilePath);
        Map<String, Integer> studentSubjectCount = csvReader.getStudentSubjectCount();

        // Verificar los resultados esperados
        assertEquals(17, studentSubjectCount.get("Olivia Red"));
        assertEquals(15, studentSubjectCount.get("Quincy Johnson"));
        assertEquals(16, studentSubjectCount.get("Kelly Pink"));
    }

    @Test
    public void testCsvWriter_writesCorrectly() throws IOException {
        // Datos de ejemplo para escribir
        Map<String, Integer> data = Map.of(
                "Olivia Red", 17,
                "Quincy Johnson", 15,
                "Kelly Pink", 16
        );

        // Ruta al archivo solution.csv
        Path outputFilePath = Files.createTempFile("solution", ".csv");

        // Escribir los datos
        csvWriter.write(outputFilePath.toString(), data);

        // Leer el contenido del archivo de salida
        String outputContent = Files.readString(outputFilePath);

        // Verificar que el contenido es correcto
        assertTrue(outputContent.contains("Olivia Red,17"));
        assertTrue(outputContent.contains("Quincy Johnson,15"));
        assertTrue(outputContent.contains("Kelly Pink,16"));

        // Eliminar archivo temporal
        Files.deleteIfExists(outputFilePath);
    }

    @Test
    public void testCsvExport_createsExpectedOutput() throws IOException {
        // Ruta al archivo input.csv
        String inputFilePath = "src/main/resources/input.csv";

        // Ruta al archivo solution.csv
        Path outputFilePath = Files.createTempFile("solution", ".csv");

        // Ejecutar el método export
        csvExport.export(inputFilePath, outputFilePath.toString());

        // Leer el contenido del archivo de salida
        String outputContent = Files.readString(outputFilePath);

        // Verificar que el archivo de salida contiene los resultados esperados
        assertTrue(outputContent.contains("Olivia Red,1"));
        assertTrue(outputContent.contains("Quincy Johnson,1"));
        assertTrue(outputContent.contains("Kelly Pink,1"));

        // Eliminar archivo temporal
        Files.deleteIfExists(outputFilePath);
    }
}
