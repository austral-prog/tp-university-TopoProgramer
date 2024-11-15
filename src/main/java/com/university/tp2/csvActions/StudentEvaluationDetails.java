package com.university.tp2.csvActions;

public class StudentEvaluationDetails {
    private String studentName;
    private String subject;
    private String evaluationType;
    private String evaluationName;
    private double grade;

    public StudentEvaluationDetails(String studentName, String subject, String evaluationType, String evaluationName, double grade) {
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public double getGrade() {
        return grade;
    }
}
