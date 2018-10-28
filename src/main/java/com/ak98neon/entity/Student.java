package com.ak98neon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private int age;
    private int course;
    private int salary;

    public Student(String name, int age, int course, int salary) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.salary = salary;
    }
}
