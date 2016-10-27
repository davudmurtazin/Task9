package by.tc.tester.service;

import by.tc.tester.bean.entity.User;
import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/13/2016.
 */
public interface UserService{

    LoginResponse logination(String login, String password) throws ServiceException, DAOException;

    int registration(String name, String login, String password, String rights) throws ServiceException, DAOException, SQLException;

    ArrayList<User> getUsers(String rights)throws ServiceException, DAOException;
}
