package com.university.testTP2;

import com.university.tp2.csvActions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvActionsTest {

    private String testInputPath;
    private String testOutputPath;

    @BeforeEach
    public void setUp() throws IOException {
        // Crear archivos temporales para las pruebas
        testInputPath = Files.createTempFile("testInput", ".csv").toString();
        testOutputPath = Files.createTempFile("testSolution", ".csv").toString();

        // Crear un archivo CSV de entrada de prueba
        try (FileWriter writer = new FileWriter(testInputPath)) {
            writer.write("Student_Name,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade\n");
            writer.write("John Doe,Mathematics,FINAL_PRACTICAL_WORK,Final Exam 1,Exercise 1,95.0\n");
            writer.write("Jane Smith,Mathematics,WRITTEN_EXAM,Written Test 1,Exercise 2,87.5\n");
            writer.write("John Doe,Mathematics,ORAL_EXAM,Oral Test 1,Exercise 3,90.0\n");
            writer.write("Jane Smith,Mathematics,PRACTICAL_WORK,Practical Work 1,Exercise 4,85.0\n");
        }
    }

    @Test
    public void testCsvReaderTp2() throws IOException {
        CsvReader_tp2 csvReader = new CsvReader_tp2();
        csvReader.read(testInputPath);

        List<StudentEvaluationDetails> details = csvReader.getStudentEvaluationDetails();
        assertNotNull(details);
        assertEquals(4, details.size());

        // Validar datos
        assertEquals("John Doe", details.get(0).getStudentName());
        assertEquals("Mathematics", details.get(0).getSubject());
        assertEquals("FINAL_PRACTICAL_WORK", details.get(0).getEvaluationType());
        assertEquals(95.0, details.get(0).getGrade());
    }

    @Test
    public void testCsvWriteableTp2() throws IOException {
        CsvReader_tp2 csvReader = new CsvReader_tp2();
        csvReader.read(testInputPath);
        List<StudentEvaluationDetails> details = csvReader.getStudentEvaluationDetails();

        CsvWriteable_tp2 csvWriter = new CsvWriteable_tp2();
        csvWriter.write(testOutputPath, details);

        // Verificar que el archivo de salida se haya generado
        File outputFile = new File(testOutputPath);
        assertTrue(outputFile.exists());

        // Verificar contenido del archivo de salida
        List<String> lines = Files.readAllLines(Paths.get(testOutputPath));
        assertEquals(5, lines.size()); // Cabecera + 4 filas de datos
        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", lines.get(0));
    }

    @Test
    public void testCsvExtractProcess() throws IOException {
        CsvExtract csvExtract = new CsvExtract();
        csvExtract.processCsv(testInputPath, testOutputPath);

        // Verificar que el archivo de salida se haya generado y contenga el formato esperado
        List<String> lines = Files.readAllLines(Paths.get(testOutputPath));
        assertNotNull(lines);
        assertTrue(lines.size() > 1); // Verificar que hay más de una línea (cabecera + contenido)

        // Validar una línea de datos específicos
        String[] data = lines.get(1).split(",");
        assertEquals("Mathematics", data[0]);
        assertEquals("Final Exam 1", data[1]);
        assertEquals("John Doe", data[2]);
        assertEquals("95.00", data[3]);
    }

    @Test
    public void testGenerateSolutionWithValidData() {
        SolutionProcessor processor = new SolutionProcessor();
        List<StudentEvaluationDetails> results = new ArrayList<>(List.of(
                new StudentEvaluationDetails("Jane Doe", "Math", "Midterm", "Midterm Exam", 85.5),
                new StudentEvaluationDetails("John Smith", "Math", "Midterm", "Midterm Exam", 78.0)
        ));

        String outputPath = "testOutput.csv";
        processor.generateSolution(results, outputPath);

        // Verificar que el archivo se haya creado y contenga las líneas esperadas
        try (BufferedReader reader = new BufferedReader(new FileReader(outputPath))) {
            assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", reader.readLine());
            assertEquals("Math,Midterm Exam,Jane Doe,85.50", reader.readLine());
            assertEquals("Math,Midterm Exam,John Smith,78.00", reader.readLine());
        } catch (IOException e) {
            fail("No se pudo leer el archivo de salida");
        }
    }
}

