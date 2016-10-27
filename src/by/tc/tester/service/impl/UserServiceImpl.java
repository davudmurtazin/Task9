package by.tc.tester.service.impl;

import by.tc.tester.bean.entity.User;
import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.dao.FactoryDAO;
import by.tc.tester.dao.UserDAO;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.UserService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/16/2016.
 */
public class UserServiceImpl implements UserService {

    @Override
    public LoginResponse logination(String login, String password) throws ServiceException, DAOException {
        if (login.isEmpty() || password.isEmpty()) {
            throw new ServiceException("Arguments for loginagion are empty!");
        }
        try{
            UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
            LoginResponse loginResponse = userDAO.logination(login,password);
            if (!loginResponse.isErrorStatus()) {
                return loginResponse;
            } else {
                return loginResponse;
            }
        }catch (DAOException e){
            throw new DAOException("Registration fail!");
        }
    }

    @Override
    public int registration(String name, String login, String password, String rights) throws ServiceException, DAOException, SQLException {
        int userID;
        if (name.isEmpty() || login.isEmpty() || password.isEmpty() || rights.isEmpty()) {
            throw new ServiceException("Arguments for registration are empty!");
        } else {
            try{
                UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
                userID = userDAO.registration(name, login, password, rights);
            }catch (DAOException e){
                throw new DAOException("Registration fail!");
            }
        }
        return userID;
    }

    @Override
    public ArrayList<User> getUsers(String rights) throws ServiceException, DAOException {
        UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
        ArrayList<User> users = userDAO.getUsers(rights);
        return users;
    }
}
