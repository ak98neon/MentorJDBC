package com.ak98neon.util;

import com.ak98neon.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorkBaseTest {
    private static Student student = new Student("test", 20, 1, 20000);

    @Before
    public void initAndInsertStudent() {
        WorkBase.createTable();
        WorkBase.insertStudent(student);
    }

    @Test
    public void create_RequestToCreate_True() {
        assertTrue(WorkBase.createTable());
    }

    @Test
    public void insert_Student_True() {
        final boolean res = WorkBase.insertStudent(student);
        assertTrue(res);
    }

    @Test
    public void delete_RequestToDeleteStudent_True() {
        assertTrue(WorkBase.deleteStudent(student));
    }

    @Test
    public void update_NameAndAgeAndCourseAndSalaryAndId_True() {
        assertTrue(WorkBase.updateStudent("newTEST", 19, 2, 30000, student.getId()));
    }

    @Test
    public void select_StudentName_ObjectStudent() {
        Student s = WorkBase.selectStudent(student.getName());
        assert s != null;
        assertNotNull(s.getName());
    }

    @Test
    public void select_RequestToSelectAll_True() {
        List<Student> list = WorkBase.selectAllStudent();
        assertTrue(list.size() > 0);
    }

    @Test
    public void drop_RequestToDropTable_True() {
        assertTrue(WorkBase.dropTable());
    }
}
