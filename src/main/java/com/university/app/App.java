package com.university.app;

import com.university.cli.Application;

import com.university.tp1.CsvExport_tp1;
import com.university.tp1.CsvReader_tp1;
import com.university.tp1.CsvWriter_tp1;
import com.university.tp2.csvActions.CsvExtract;
import com.university.tp3.AppExecutor;

import java.io.IOException;

public class App {
        public static void main(String[] args) throws IOException {

                /*TP1*/
                String inputFilePath = "src/main/resources/input.csv";
                String outputFilePath = "src/main/resources/solution.csv";


                CsvReader_tp1 csvReaderTp1 = new CsvReader_tp1();
                CsvWriter_tp1 csvWriterTp1 = new CsvWriter_tp1();
                CsvExport_tp1 csvExportTp1 = new CsvExport_tp1(csvReaderTp1, csvWriterTp1);

                csvExportTp1.export(inputFilePath, outputFilePath);


                /*TP2*/
                String inputFilePath2 = "src/main/resources/input_2.csv";
                String outputFilePath2 = "src/main/resources/solution2.csv";

                CsvExtract csvExtract = new CsvExtract();

                csvExtract.processCsv(inputFilePath2, outputFilePath2);

                /*TP3*/
                String inputFilePath3 = "src/main/resources/input_3.csv";
                String expectedFilePath3 = "src/main/resources/expected_3.csv";

                AppExecutor executor = new AppExecutor();
                executor.execute(inputFilePath3, outputFilePath2, expectedFilePath3);

                /*TP4*/
                Application app = new Application();
                app.start();
        }
}
