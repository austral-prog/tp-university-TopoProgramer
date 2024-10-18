package com.university;

public class Students {
    private String classroom;
    private String subject;
    private String studentName;
    private String studentEmail;
    private String subjectTeacher;

    // Constructor
    public Students(String classroom, String subject, String studentName, String studentEmail, String subjectTeacher) {
        this.classroom = classroom;
        this.subject = subject;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subjectTeacher = subjectTeacher;
    }

    // Getters y Setters
    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return "Students{" +
                "classroom='" + classroom + '\'' +
                ", subject='" + subject + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", subjectTeacher='" + subjectTeacher + '\'' +
                '}';
    }
}
