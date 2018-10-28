package com.ak98neon.util;

/**
 * Class where we store all requests to the database
 */
class Queries {
    private Queries() {
    }

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "STUD (ID INT NOT NULL AUTO_INCREMENT, "
            + "NAME VARCHAR(45) NOT NULL, "
            + "AGE INT, "
            + "COURSE INT, "
            + "SALARY INT, "
            + "PRIMARY KEY(ID))";
    static final String INSERT_STUDENT = "INSERT INTO STUD (NAME, AGE, COURSE, SALARY) VALUES (?, ?, ?, ?);";
    static final String SELECT_STUDENT = "SELECT ID, NAME, AGE, COURSE, SALARY FROM STUD WHERE NAME = ?;";
    static final String UPDATE_STUDENT = "UPDATE STUD SET NAME = ?, AGE = ?, COURSE = ?, SALARY = ? WHERE ID = ?;";
    static final String DELETE_STUDENT = "DELETE FROM STUD WHERE ID = ?;";
    static final String SELECT_ALL_STUDENT = "SELECT * FROM STUD;";
    static final String DROP_TABLE = "DROP TABLE STUD";
}
