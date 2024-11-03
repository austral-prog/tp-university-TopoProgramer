package com.university.Utils.TP1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class csvTP1Writer implements csvWrite {

    public void writeCSVFromMap(Map<String, Integer> data, String outputFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Escribir el header
            bw.write("Student_Name,Course_Count\n");

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String studentName = entry.getKey();
                Integer courseCount = entry.getValue();

                // Escribir la línea con el nombre del estudiante y la cantidad de cursos
                bw.write(studentName + "," + courseCount + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
