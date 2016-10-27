package by.tc.tester.dao;

import by.tc.tester.bean.entity.User;
import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

	LoginResponse logination(String login, String password) throws DAOException;

	int registration(String name, String login, String password, String rights) throws DAOException, SQLException;

	ArrayList<User> getUsers(String rights)throws ServiceException, DAOException;
}
