package com.university.App;


import com.university.Utils.TP1.csvAction;


import com.university.Utils.TP2.CSVActions.csvReader;
import com.university.Utils.TP2.CSVActions.csvWriter;
import com.university.Utils.TP2.gradeCalculator;
import com.university.Utils.TP2.examProcessor;
import com.university.Utils.TP2.solutionProcessor; // Importar solutionProcessor



public class App {
    public static void main(String[] args) {

        //Tp1
        csvAction action=new csvAction();

        //Tp2
        csvReader reader = new csvReader();
        csvWriter writer = new csvWriter();
        gradeCalculator calculator = new gradeCalculator();
        solutionProcessor processor = new solutionProcessor(); // Instanciar solutionProcessor


        action.processCSV("src/main/resources/input.csv", "src/main/resources/solution.csv");

        examProcessor examProc = new examProcessor(reader, writer, calculator, processor); // Pasar la instancia
        examProc.processExams("src/main/resources/input_2.csv", "src/main/resources/solution2.csv");
        }
}