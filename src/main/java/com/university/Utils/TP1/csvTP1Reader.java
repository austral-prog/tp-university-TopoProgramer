package com.university.Utils.TP1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;



public class csvTP1Reader implements csvRead {
    @Override
    public Map<String, List<String>> readCSVAsMap(String filePath) {
        Map<String, List<String>> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(",");

                for (String header : headers) {
                    data.put(header, new ArrayList<>());
                }

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    for (int i = 0; i < headers.length; i++) {
                        String header = headers[i];
                        String value = i < values.length ? values[i] : "";
                        data.get(header).add(value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
