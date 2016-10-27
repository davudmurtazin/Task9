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

/**
 * Created by Davud_Murtazin on 10/13/2016.
 */
public class Login implements Command{

    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException {
        LoginRequest req;

        if(request instanceof LoginRequest){
            req = (LoginRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        String login = req.getLogin();
        String password = req.getPassword();

        ServiceFactory service = ServiceFactory.getInstance();
        UserService userService = service.getUserService();

        LoginResponse response = new LoginResponse();
        try{
            if (!userService.logination(login, password).isErrorStatus()) {
                response.setUserID(userService.logination(login, password).getUserID());
                response.setRights(userService.logination(login, password).getRights());
                response.setErrorStatus(false);
                response.setResultMessage("Logination access!");
            }else {
                response.setErrorStatus(true);
                response.setResultMessage("Logination failed!");
            }
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }
        return response;
    }
}
