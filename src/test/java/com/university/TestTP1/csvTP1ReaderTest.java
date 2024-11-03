package com.university.TestTP1;


import com.university.Utils.TP1.csvTP1Reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class csvTP1ReaderTest {

    private csvTP1Reader reader;

    @BeforeEach
    public void setUp() {
        reader = new csvTP1Reader();
    }

    @Test
    public void testReadCSVAsMap() {
        String filePath = "src/test/resources/input_tp1.csv";
        Map<String, List<String>> data = reader.readCSVAsMap(filePath);

        // Verificamos que el archivo se ha leído correctamente
        assertNotNull(data);
        assertEquals(2, data.size()); // Ejemplo: número de columnas esperadas en el CSV
        assertEquals(3, data.get("Student_Name").size()); // Ejemplo: número de registros en "Student_Name"
    }
}
