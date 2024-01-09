package com.example.demo.Model;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String course;

    public Student(int id, String name, String surname, String course) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
