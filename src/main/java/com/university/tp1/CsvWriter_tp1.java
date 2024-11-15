package com.university.tp1;

import com.university.interfaces.CsvWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvWriter_tp1 implements CsvWrite<Map<String, Integer>> {

    @Override
    public void write(String filePath, Map<String, Integer> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Student Name,Subject Count\n"); // Escribir encabezado

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }
}
