package by.tc.tester.dao;

import by.tc.tester.dao.impl.mysql.MySQLTestDAO;
import by.tc.tester.dao.impl.mysql.MySQLUserDAO;

/**
 * Created by Davud_Murtazin on 10/12/2016.
 */
public class FactoryDAO {
    private static final FactoryDAO instance = new FactoryDAO();

    private TestDAO testDAO = new MySQLTestDAO();
    private UserDAO userDAO = new MySQLUserDAO();

    public static FactoryDAO getInstance(){
        return instance;
    }

    public TestDAO getTestDAO(){
        return testDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
