package com.university;

public class App {
    public static void main(String[] args) {
        CSVActions csvActions = new CSVActions();
        csvActions.processCSV("src/main/resources/input.csv", "src/main/resources/solutions.csv");
    }
}
