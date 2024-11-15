package com.university.testTP3;

import com.university.tp3.ApprovalCriteria;
import com.university.tp3.csvActions.CsvReader_tp3;
import com.university.tp3.csvActions.CsvWriter_tp3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvActionsTest_tp3 {

    private CsvReader_tp3 csvReader;
    private CsvWriter_tp3 csvWriter;
    private String inputCriteriaPath;
    private String solutionPath;
    private String expectedOutputPath;

    @BeforeEach
    public void setUp() throws IOException {
        csvReader = new CsvReader_tp3();
        csvWriter = new CsvWriter_tp3();
        inputCriteriaPath = Files.createTempFile("testInputCriteria", ".csv").toString();
        solutionPath = Files.createTempFile("testSolution", ".csv").toString();
        expectedOutputPath = Files.createTempFile("testExpectedOutput", ".csv").toString();
    }

    @Test
    public void testReadCriteriaFromFile() throws IOException {
        // Setup: create a sample input_3.csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputCriteriaPath))) {
            writer.write("Subject,CriteriaType,CriteriaValue,Evaluations\n");
            writer.write("Math,AVERAGE_ABOVE_VALUE,6.0,Exam1,Exam2\n");
            writer.write("Physics,MAX_ABOVE_VALUE,7.5,Exam1,Quiz1\n");
        }

        // Action: read the criteria
        List<ApprovalCriteria> criteriaList = csvReader.read(inputCriteriaPath);

        // Assertion
        assertEquals(2, criteriaList.size());
        assertEquals("Math", criteriaList.get(0).getSubject());
        assertEquals("AVERAGE_ABOVE_VALUE", criteriaList.get(0).getCriteriaType());
        assertEquals(6.0, criteriaList.get(0).getCriteriaValue());
        assertEquals(2, criteriaList.get(0).getEvaluationNames().size());
    }

    @Test
    public void testEvaluateAndWrite() throws IOException {
        // Setup: create sample input_3.csv and solution2.csv files
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputCriteriaPath))) {
            writer.write("Subject,CriteriaType,CriteriaValue,Evaluations\n");
            writer.write("Math,AVERAGE_ABOVE_VALUE,6.0,Exam1,Exam2\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(solutionPath))) {
            writer.write("Subject,EvaluationName,StudentName,Grade\n");
            writer.write("Math,Exam1,John Doe,6.5\n");
            writer.write("Math,Exam2,John Doe,7.0\n");
            writer.write("Math,Exam1,Jane Smith,5.0\n");
            writer.write("Math,Exam2,Jane Smith,5.5\n");
        }

        // Action: evaluate and write to expected_3.csv
        List<ApprovalCriteria> criteriaList = csvReader.read(inputCriteriaPath);
        csvWriter.evaluateAndWrite(solutionPath, expectedOutputPath, criteriaList);

        // Verify output
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedOutputPath))) {
            String header = reader.readLine();
            System.out.println("Header: " + header);  // Debugging line
            assertEquals("Subject_Name,Student_Name,Approve", header);

            String firstLine = reader.readLine();
            System.out.println("First Line: " + firstLine);  // Debugging line
            assertEquals("Math,Jane Smith,NO", firstLine);


            String secondLine = reader.readLine();
            System.out.println("Second Line: " + secondLine);  // Debugging line
            assertEquals("Math,John Doe,YES", secondLine);
        }
    }

    @Test
    public void testMalformedLineHandling() throws IOException {
        // Setup: create a sample input file with malformed lines
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputCriteriaPath))) {
            writer.write("Subject,CriteriaType,CriteriaValue,Evaluations\n");
            writer.write("Math,AVERAGE_ABOVE_VALUE,6.0\n"); // Malformed (missing evaluations)
            writer.write("Physics,MAX_ABOVE_VALUE,7.5,Exam1,Quiz1\n");
        }

        // Action: read the criteria
        List<ApprovalCriteria> criteriaList = csvReader.read(inputCriteriaPath);

        // Assertion: should only include the well-formed line
        assertEquals(1, criteriaList.size());
        assertEquals("Physics", criteriaList.get(0).getSubject());
    }
}
