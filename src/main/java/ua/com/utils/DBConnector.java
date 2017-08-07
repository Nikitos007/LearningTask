package ua.com.utils;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector {

    private static final Logger LOG = Logger.getLogger(DBConnector.class);

    private DataSource dataSource = null;

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/db_department");
            } catch (NamingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return dataSource.getConnection();
    }


    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }


    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

}
