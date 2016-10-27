package by.tc.tester.command.impl.user;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.user.ShowUserRequest;
import by.tc.tester.bean.request.user.ShowUserResponse;
import by.tc.tester.command.Command;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.ServiceFactory;
import by.tc.tester.service.TestService;
import by.tc.tester.service.UserService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class ShowUser implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof ShowUserRequest) {
            String rights = ((ShowUserRequest) request).getRights();

            ServiceFactory service = ServiceFactory.getInstance();
            UserService userService = service.getUserService();

            ShowUserResponse response = new ShowUserResponse();
            response.setUsers(userService.getUsers(rights));
            response.setErrorStatus(false);
            response.setResultMessage("Got all users!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
