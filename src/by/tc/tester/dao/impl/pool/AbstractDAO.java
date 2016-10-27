package by.tc.tester.dao.impl.pool;

import by.tc.tester.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Davud_Murtazin on 10/12/2016.
 */
public class AbstractDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected Connection getConnection() throws DAOException {
        return connectionPool.getConnection();
    }

    protected void releaseConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) throws DAOException {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DAOException("Couldn't close prepared statement");
        }
    }
}
