package fr.epita.cardgame.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc

/**
 * @author Dogara Ishaku
 *
 */
public class DBConnection {
    /**
     * Gets a MySQL database connection.
     *
     * @return the connection
     * @throws SQLException the SQL exception
     */
    public Connection getConnection() throws SQLException {
        Configuration conf = Configuration.getInstance();
        String jdbcUrl = conf.getConfigurationValue("jdbc.url");
        String user = conf.getConfigurationValue("jdbc.user");
        String password = conf.getConfigurationValue("jdbc.password");
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}

