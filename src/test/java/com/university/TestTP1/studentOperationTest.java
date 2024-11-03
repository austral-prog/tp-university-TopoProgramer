package com.university.TestTP1;

import com.university.Utils.TP1.studentOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class studentOperationTest {

    private studentOperation operation;

    @BeforeEach
    public void setUp() {
        operation = new studentOperation();
    }

    @Test
    public void testAggregateByStudent() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("Student_Name", Arrays.asList("Alice Azure", "Bob Brown", "Alice Azure"));
        data.put("Subject", Arrays.asList("Math", "Physics", "Math"));

        Map<String, Integer> result = operation.aggregateByStudent(data);

        // Verificamos la cantidad de materias distintas para cada estudiante
        assertEquals(1, result.get("Alice Azure")); // Alice Azure debería tener 1 materia distinta
        assertEquals(1, result.get("Bob Brown")); // Bob Brown debería tener 1 materia
    }
}
