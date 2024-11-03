package com.university.Data;

public class result {
    private String subject;
    private String evaluationName;
    private String studentName;
    private double grade;

    public result(String subject, String evaluationName, String studentName, double grade) {
        this.subject = subject;
        this.evaluationName = evaluationName;
        this.studentName = studentName;
        this.grade = grade;
    }

    public String getSubject() {

        return subject;
    }
    public String getEvaluationName() {

        return evaluationName;
    }
    public String getStudentName() {

        return studentName;
    }
    public double getGrade() {

        return grade;
    }
}
