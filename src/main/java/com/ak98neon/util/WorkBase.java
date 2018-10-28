package com.ak98neon.util;

import com.ak98neon.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WorkBase {
    private WorkBase() {
    }

    /**
     * Method createTable, Creates a table in a database.
     *
     * @return true if successfully create table
     */
    public static boolean createTable() {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.CREATE_TABLE)) {
            final int resStatement = statement.executeUpdate();
            if (resStatement == 0) {
                log.info("table has been is created");
                return true;
            }
        } catch (SQLException e) {
            log.info("Table is not created, error: {}", e.getSQLState());
        }
        return false;
    }

    /**
     * Inserts a new entry into the table.
     *
     * @param student Whose student we insert
     * @return true if successfully insert student
     */
    public static boolean insertStudent(final Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.INSERT_STUDENT)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getCourse());
            statement.setDouble(4, student.getSalary());
            final int resStatement = statement.executeUpdate();
            if (resStatement > 0) {
                log.info("Record is inserted into table!");
                return true;
            }
        } catch (SQLException e) {
            log.info("Insert error: {}", e.getSQLState());
        }
        return false;
    }

    /**
     * We update the information about the already created student
     *
     * @return true if successfully update record int data base
     */
    public static boolean updateStudent(final String name, final int age, final int course, final int salary,
                                        final int id) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.UPDATE_STUDENT)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, course);
            statement.setDouble(4, salary);
            statement.setInt(5, id);
            final int resStatement = statement.executeUpdate();
            if (resStatement == 0) {
                log.info("Record is updated to table!");
                return true;
            }
        } catch (SQLException e) {
            log.info("Update error: {}", e.getSQLState());
        }
        return false;
    }

    /**
     * Removing student
     *
     * @param student student object
     * @return true if successfully delete
     */
    public static boolean deleteStudent(final Student student) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.DELETE_STUDENT)) {
            statement.setString(1, student.getName());
            final int resStatement = statement.executeUpdate();
            if (resStatement > 0) {
                log.info("Record is delete to table");
                return true;
            }
        } catch (SQLException e) {
            log.info("Delete error: {}", e.getSQLState());
        }
        return false;
    }

    /**
     * Choosing a student by name
     *
     * @param name name of student
     * @return Student
     */
    public static Student selectStudent(final String name) {
        try (PreparedStatement statement = DBWorker.getConnection().prepareStatement(Queries.SELECT_STUDENT)) {
            Student student = new Student();
            statement.setString(1, name);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    student.setId(set.getInt("id"));
                    student.setName(set.getString("name"));
                    student.setAge(set.getInt("age"));
                    student.setCourse(set.getInt("course"));
                    student.setSalary(set.getInt("salary"));
                }
                log.info("select is complete");
                return student;
            }
        } catch (SQLException e) {
            log.info("Select error: {}", e.getSQLState());
        }
        return null;
    }

    /**
     * Select all students
     *
     * @return List Student
     */
    public static List<Student> selectAllStudent() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.SELECT_ALL_STUDENT)) {
            List<Student> list = new ArrayList<>();
            try (ResultSet set = preparedStatement.executeQuery()) {
                while (set.next()) {
                    int id = set.getInt("id");
                    String name = set.getString("name");
                    int age = set.getInt("age");
                    int course = set.getInt("course");
                    int salary = set.getInt("salary");
                    Student stud = new Student(id, name, age, course, salary);
                    list.add(stud);
                }
            }
            log.info("select all record student");
            return list;
        } catch (SQLException e) {
            log.info("select all error: {}", e.getSQLState());
        }
        return new ArrayList<>();
    }

    /**
     * Drop table from data base
     *
     * @return true if successfully drop table
     */
    public static boolean dropTable() {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(Queries.DROP_TABLE)) {
            final int result = preparedStatement.executeUpdate();
            if (result == 0) {
                log.info("Table is drop");
                return true;
            }
        } catch (SQLException e) {
            log.info("drop table error: {}", e.getSQLState());
        }
        return false;
    }
}
