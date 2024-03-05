package edu.java.scrapper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MigrationTest extends IntegrationEnvironment {

    @Test
    void testDatabaseConnectionAndTableCreation() {
        try (Connection connection = DriverManager.getConnection(
            POSTGRES.getJdbcUrl(),
            POSTGRES.getUsername(),
            POSTGRES.getPassword()
        )) {
            assertTrue(connection.isValid(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTablesCreation() throws SQLException {
        assertTrue(tableExists("chat"));
        assertTrue(tableExists("link"));
        assertTrue(tableExists("chat_link"));
    }


    public static boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
            return tables.next();
        }
    }
}
