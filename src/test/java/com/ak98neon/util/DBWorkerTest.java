package com.ak98neon.util;

import com.ak98neon.util.DBWorker;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DBWorkerTest {
    @Test
    public void connection_GetConnection_Connection() throws SQLException {
        Connection connection = DBWorker.getConnection();
        assertNotNull(connection);
    }
}
