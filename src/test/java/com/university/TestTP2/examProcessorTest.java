package com.university.TestTP2;

import com.university.Utils.TP2.CSVActions.csvReader;
import com.university.Utils.TP2.CSVActions.csvWriter;
import com.university.Utils.TP2.examProcessor;
import com.university.Utils.TP2.gradeCalculator;
import com.university.Utils.TP2.solutionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.university.Data.result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class examProcessorTest {

    private examProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new examProcessor(new csvReader(), new csvWriter(), new gradeCalculator(), new solutionProcessor());
    }

    @Test
    public void testProcessExams() {
        String inputFilePath = "src/test/resources/input_tp2.csv";
        String outputFilePath = "src/test/resources/output_tp2.csv";

        // Llamamos al método de procesamiento
        processor.processExams(inputFilePath, outputFilePath);

        // Leemos y verificamos el resultado en el archivo de salida
        List<result> results = processor.getResults();
        assertEquals("Math", results.get(0).getSubject()); // Ejemplo de comprobación
    }
}
