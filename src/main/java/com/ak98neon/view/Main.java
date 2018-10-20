package com.ak98neon.view;

import com.ak98neon.controller.DBWorker;
import com.ak98neon.controller.Queries;
import com.ak98neon.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class, where we contain all methods for job bd
 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "TestName", 20, 1, 100);
        createTable();
        insertStudent(student);
        selectAllStudent().forEach(System.out::println);
    }

    /**
     * Method createTable, Creates a table in a database.
     * @return Successful or not the table was created
     */
    public static boolean createTable() {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.CREATE_TABLE)) {
            statement.executeUpdate();
            System.out.println("Table " + "testTable" + " is created!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Inserts a new entry into the table.
     * @param student Whose student we insert
     */
    public static void insertStudent(Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.INSERT_STUDENT)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getCourse());
            statement.setDouble(4, student.getSalary());
            statement.executeUpdate();
            System.out.println("Record is inserted into STUD table!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * We update the information about the already created student
     * @param studentOld
     * @param studentNew
     */
    public static void updateStudent(Student studentOld, Student studentNew) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.UPDATE_STUDENT)) {
            statement.setString(1, studentNew.getName());
            statement.setInt(2, studentNew.getAge());
            statement.setInt(3, studentNew.getCourse());
            statement.setDouble(4, studentNew.getSalary());
            statement.setInt(5, studentOld.getId());
            statement.executeUpdate();
            System.out.println("Record is updated to STUD table!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removing student
     * @param student
     * @return Successfully deleted or not
     */
    public static boolean deleteStudent(Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.DELETE_STUDENT)) {
            statement.setString(1, student.getName());
            statement.executeUpdate();
            System.out.println("Record is delete to STUD table");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Choosing a student by name
     * @param name
     * @return Student
     */
    public static Student selectStudent(String name) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.SELECT_STUDENT)) {
            Student student = new Student();
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                student.setId(set.getInt("id"));
                student.setName(set.getString("name"));
                student.setAge(set.getInt("age"));
                student.setCourse(set.getInt("course"));
                student.setSalary(set.getInt("salary"));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select all students
     * @return List Student
     */
    private static List<Student> selectAllStudent() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.SELECT_ALL_STUDENT)) {
            List<Student> list = new ArrayList<>();
            ResultSet set = preparedStatement.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Drop table drom data base
     * @return Successful drop table or not
     */
    public static boolean dropTable() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.DROP_TABLE)) {
            preparedStatement.executeUpdate();
            System.out.println("Table drop");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
