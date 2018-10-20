package com.ak98neon.controller;

/**
 * Class where we store all requests to the database
 */
public class Queries {
    private Queries() {
    }

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "STUD (ID INT NOT NULL AUTO_INCREMENT, "
            + "NAME VARCHAR(45) NOT NULL, "
            + "AGE INT, "
            + "COURSE INT, "
            + "SALARY INT, "
            + "PRIMARY KEY(ID))";
    public static final String INSERT_STUDENT = "INSERT INTO STUD (NAME, AGE, COURSE, SALARY) VALUES (?, ?, ?, ?);";
    public static final String SELECT_STUDENT = "SELECT ID, NAME, AGE, COURSE, SALARY FROM STUD WHERE NAME = ?;";
    public static final String UPDATE_STUDENT = "UPDATE STUD SET NAME = ?, AGE = ?, COURSE = ?, SALARY = ? WHERE ID = ?;";
    public static final String DELETE_STUDENT = "DELETE FROM STUD WHERE NAME = ?;";
    public static final String SELECT_ALL_STUDENT = "SELECT * FROM STUD;";
    public static final String DROP_TABLE = "DROP TABLE STUD";
}
