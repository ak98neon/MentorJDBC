package com.ak98neon.view;

import com.ak98neon.entity.Student;
import com.ak98neon.util.NumberPercent;
import com.ak98neon.util.WorkBase;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class, where we contain all methods for job bd
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "TestName", 20, 1, 100);
        WorkBase.createTable();
        WorkBase.insertStudent(student);
        WorkBase.updateStudent("TESTNAMEEE", 19, 2, 200, student.getId());
        WorkBase.deleteStudent(student);
        WorkBase.selectAllStudent().forEach((Student i) -> log.info("{}", i));
        WorkBase.dropTable();

        log.info(NumberPercent.calculatePercent(1,3,2) + "%");
    }
}
