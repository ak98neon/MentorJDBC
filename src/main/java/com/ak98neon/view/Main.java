package com.ak98neon.view;

import com.ak98neon.controller.DBWorker;
import com.ak98neon.controller.Queries;
import com.ak98neon.model.Student;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class, where we contain all methods for job bd
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "TestName", 20, 1, 100);
        createTable();
        insertStudent(student);
        updateStudent(student, new Student("LOL", 20, 2, 2));
        deleteStudent(student);
        selectAllStudent().forEach(System.out::println);
        dropTable();
    }

    /**
     * Method createTable, Creates a table in a database.
     */
    public static void createTable() {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.CREATE_TABLE)) {
            statement.executeUpdate();
            log.info("table has been is created");
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
    }

    /**
     * Inserts a new entry into the table.
     *
     * @param student Whose student we insert
     */
    public static void insertStudent(Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.INSERT_STUDENT)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getCourse());
            statement.setDouble(4, student.getSalary());
            statement.executeUpdate();
            log.info("Record is inserted into table!");
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
    }

    /**
     * We update the information about the already created student
     *
     * @param studentOld old student object
     * @param studentNew new student object
     */
    private static void updateStudent(Student studentOld, Student studentNew) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.UPDATE_STUDENT)) {
            statement.setString(1, studentNew.getName());
            statement.setInt(2, studentNew.getAge());
            statement.setInt(3, studentNew.getCourse());
            statement.setDouble(4, studentNew.getSalary());
            statement.setInt(5, studentOld.getId());
            statement.executeUpdate();
            log.info("Record is updated to STUD table!");
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
    }

    /**
     * Removing student
     *
     * @param student student object
     */
    private static void deleteStudent(Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.DELETE_STUDENT)) {
            statement.setString(1, student.getName());
            statement.executeUpdate();
            log.info("Record is delete to STUD table");
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
    }

    /**
     * Choosing a student by name
     *
     * @param name name of student
     * @return Student
     */
    public static Student selectStudent(String name) {
        ResultSet set = null;
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.SELECT_STUDENT)) {
            Student student = new Student();
            statement.setString(1, name);
            set = statement.executeQuery();
            while (set.next()) {
                student.setId(set.getInt("id"));
                student.setName(set.getString("name"));
                student.setAge(set.getInt("age"));
                student.setCourse(set.getInt("course"));
                student.setSalary(set.getInt("salary"));
            }
            return student;
        } catch (SQLException e) {
            log.info(e.getSQLState());
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                log.info(e.getSQLState());
            }
        }
        return null;
    }

    /**
     * Select all students
     *
     * @return List Student
     */
    private static List<Student> selectAllStudent() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.SELECT_ALL_STUDENT);
             ResultSet set = preparedStatement.executeQuery()) {
            List<Student> list = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                int age = set.getInt("age");
                int course = set.getInt("course");
                int salary = set.getInt("salary");
                Student stud = new Student(id, name, age, course, salary);
                list.add(stud);
            }
            return list;
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
        return new ArrayList<>();
    }

    /**
     * Drop table drom data base
     */
    private static void dropTable() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.DROP_TABLE)) {
            preparedStatement.executeUpdate();
            System.out.println("Table drop");
        } catch (SQLException e) {
            log.info(e.getSQLState());
        }
    }
}
