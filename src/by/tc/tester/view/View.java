package by.tc.tester.view;

import by.tc.tester.controller.authorization.AuthorizationMenu;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

public class View {
	public static void main(String[] args) throws ServiceException, DAOException, SQLException {
		AuthorizationMenu authMenu = new AuthorizationMenu();
		authMenu.authMenu();
	}
}
