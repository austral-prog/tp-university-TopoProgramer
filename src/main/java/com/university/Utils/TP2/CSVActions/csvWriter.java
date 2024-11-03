package com.university.Utils.TP2.CSVActions;

import com.university.Data.result;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class csvWriter implements csvWrite {

    @Override
    public void writeResultsToCSV(List<result> results, String outputFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath, StandardCharsets.UTF_8))) {
            bw.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");
            for (result res : results) {
                bw.write(res.getSubject() + "," + res.getEvaluationName() + "," + res.getStudentName() + "," + res.getGrade() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
