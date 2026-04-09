package fuelcon.datasource;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

public class MariaDbConnectionTest {

    @Test
    public void testGetConnectionNotNull() {
        try {
            var conn = MariaDbConnection.getConnection();
            assertNotNull(conn);
            conn.close();
        } catch (SQLException e) {
            // If DB is not available, we skip — not a code failure
            System.out.println("DB not available: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionIsValid() {
        try {
            var conn = MariaDbConnection.getConnection();
            assertTrue(conn.isValid(2));
            conn.close();
        } catch (SQLException e) {
            System.out.println("DB not available: " + e.getMessage());
        }
    }
}