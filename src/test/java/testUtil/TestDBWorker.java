package testUtil;

import com.ak98neon.util.DBWorker;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class TestDBWorker {
    @Test
    public void testConnection() throws SQLException {
        Connection connection = DBWorker.getConnection();
        assertNotNull(connection);
    }
}
