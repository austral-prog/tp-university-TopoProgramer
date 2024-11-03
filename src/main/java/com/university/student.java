package com.university;

public class student {
    private String studentName;
    private String subject;
    private String evaluationType;
    private String evaluationName;
    private String exerciseName;
    private double grade;
    private String classroom;
    private String studentEmail;
    private String subjectTeacher;

    public student(String studentName, String subject, String evaluationType, String evaluationName, String exerciseName, double grade) {
        this.studentName = studentName;
        this.subject = subject;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.exerciseName = exerciseName;
        this.grade = grade;
        this.classroom = classroom;
        this.studentEmail = studentEmail;
        this.subjectTeacher = subjectTeacher;
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
    public String getExerciseName() {

        return exerciseName;
    }
    public double getGrade() {

        return grade;
    }

    // Getters y Setters
    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

}
