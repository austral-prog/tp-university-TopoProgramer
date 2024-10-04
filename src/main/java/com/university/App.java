package com.university;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/students.csv";
        String outputFilePath = "src/main/resources/solution.csv";

        List<Student> students = CSVReader.readCSV(inputFilePath);
        Map<String, Integer> aggregatedData = Aggregator.aggregateData(students);
        CSVWriter.writeCSV(outputFilePath, aggregatedData);
    }

    // Class to represent a Student
    static class Student {
        private String name;
        private int courseCount;

        public Student(String name, int courseCount) {
            this.name = name;
            this.courseCount = courseCount;
        }

        public String getName() { return name; }
        public int getCourseCount() { return courseCount; }
    }

    // Class to read CSV data
    static class CSVReader {
        public static List<Student> readCSV(String filePath) {
            List<Student> students = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                br.readLine(); // Skip header
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    students.add(new Student(data[0], Integer.parseInt(data[1])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return students;
        }
    }

    // Class to aggregate data
    static class Aggregator {
        public static Map<String, Integer> aggregateData(List<Student> students) {
            Map<String, Integer> aggregation = new HashMap<>();
            for (Student student : students) {
                aggregation.merge(student.getName(), student.getCourseCount(), Integer::sum);
            }
            return aggregation;
        }
    }

    // Class to write CSV data
    static class CSVWriter {
        public static void writeCSV(String filePath, Map<String, Integer> data) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("Student_Name,Total_Course_Count\n");
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
