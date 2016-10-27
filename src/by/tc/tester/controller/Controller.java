package by.tc.tester.controller;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.command.Command;
import by.tc.tester.command.CommandHelper;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;
import by.tc.tester.validate.CommandNameValidator;

import java.sql.SQLException;

public class Controller {
	private CommandHelper helper = new CommandHelper();

	public Controller(){}
	
	public Response doRequest(Request request) throws ServiceException, DAOException, SQLException {
		Response response = null;
		if(CommandNameValidator.isValidCommandName(request.getCommandName())){
			String commandName = request.getCommandName();

			Command command = helper.getCommand(commandName);
			try {
				response = command.execute(request);
			} catch (CommandException e) {
				response = new Response();
				response.setErrorStatus(true);
				response.setErrorMessage("ERROR!");
			} catch (ServiceException e) {
				throw new ServiceException("Fault request!");
			}
		}
		else{
			System.out.println("Incorrect command name!");
		}
		return response;
	}
}
