package by.tc.tester.command;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

public interface Command {
	Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException;
}
