package com.university.TestTP1;

import com.university.Utils.TP1.csvAction;
import com.university.Utils.TP1.csvTP1Reader;
import com.university.Utils.TP1.csvTP1Writer;
import com.university.Utils.TP1.studentOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class csvActionTest {

    private csvAction action;
    private csvTP1Reader reader;
    private csvTP1Writer writer;
    private studentOperation operation;

    @BeforeEach
    public void setUp() {
        action = new csvAction();
        reader = new csvTP1Reader();
        writer = new csvTP1Writer();
        operation = new studentOperation();
    }

    @Test
    public void testProcessCSV() {
        String inputFilePath = "src/test/resources/input_tp1.csv";
        String outputFilePath = "src/test/resources/output_tp1.csv";

        // Simulamos los datos de entrada en un Map
        Map<String, List<String>> studentData = new HashMap<>();
        studentData.put("Student_Name", List.of("Alice Azure", "Bob Brown", "Alice Azure"));
        studentData.put("Subject", List.of("Math", "Physics", "History"));

        // Ejecutamos el método de procesamiento
        Map<String, Integer> resultData = operation.aggregateByStudent(studentData);

        // Comprobamos el contenido de los datos procesados
        assertEquals(2, resultData.get("Alice Azure")); // Alice Azure debería tener 2 materias distintas
        assertEquals(1, resultData.get("Bob Brown")); // Bob Brown debería tener 1 materia distinta
    }
}
