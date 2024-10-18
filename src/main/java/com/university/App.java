package com.university;

public class App {
    public static void main(String[] args) {
        CSVActions csvActions = new CSVActions();
        csvActions.processCSV("C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\input.csv", "C:\\Users\\Bruno\\OneDrive\\Escritorio\\Prog II\\Trabajo Practico Final\\src\\main\\resources\\solutions.csv");
    }
}
