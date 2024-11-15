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
                String inputFilePath = "src/main/resources/input.csv"; // Ruta al archivo de entrada (input.csv)
                String outputFilePath = "src/main/resources/solution.csv"; // Ruta al archivo de salida (output.csv)

                // Crear las instancias de los objetos necesarios
                CsvReader_tp1 csvReaderTp1 = new CsvReader_tp1();  // Instancia del lector de CSV
                CsvWriter_tp1 csvWriterTp1 = new CsvWriter_tp1();  // Instancia del escritor de CSV
                CsvExport_tp1 csvExportTp1 = new CsvExport_tp1(csvReaderTp1, csvWriterTp1);  // Instancia del exportador

                // Llamar al método export para procesar el archivo de entrada y generar el archivo de salida
                csvExportTp1.export(inputFilePath, outputFilePath);

                System.out.println("El archivo CSV ha sido procesado y exportado exitosamente.");



                /*TP2*/
                String inputFilePath2 = "src/main/resources/input_2.csv";  // Ruta del archivo de entrada
                String outputFilePath2 = "src/main/resources/solution2.csv";  // Ruta del archivo de salida

                // Crear una instancia de CsvExtract
                CsvExtract csvExtract = new CsvExtract();

                // Llamar al método para procesar el archivo CSV
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
