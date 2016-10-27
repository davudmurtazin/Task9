package by.tc.tester.command.impl.user;

import by.tc.tester.bean.request.user.*;
import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.command.Command;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.ServiceFactory;
import by.tc.tester.service.UserService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

/**
 * Created by Davud_Murtazin on 10/13/2016.
 */
public class Register implements Command {

    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        RegisterRequest req;

        if(request instanceof RegisterRequest){
            req = (RegisterRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        String name = req.getName();
        String login = req.getLogin();
        String password = req.getPassword();
        String rights = req.getRights();
        ServiceFactory service = ServiceFactory.getInstance();
        UserService userService = service.getUserService();

        try{
            userService.registration(name, login, password, rights);
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }
        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Note added!");

        return response;
    }
}
