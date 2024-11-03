package com.university.Utils.TP2.CSVActions;

import com.university.student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class csvReader implements csvRead {

    @Override
    public List<student> readStudentsFromCSV(String filePath) {
        List<student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line = br.readLine(); // Ignora encabezado
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    String name = values[0];
                    String subject = values[1];
                    String evalType = values[2];
                    String evalName = values[3];
                    String exercise = values[4];
                    double grade = Double.parseDouble(values[5]);
                    students.add(new student(name, subject, evalType, evalName, exercise, grade));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
