package by.tc.tester.controller.authorization;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.user.LoginRequest;
import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.bean.request.user.RegisterRequest;
import by.tc.tester.controller.Controller;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Davud_Murtazin on 10/16/2016.
 */
public class AuthorizationMenuUtil {

    public static LoginResponse logination() throws ServiceException, DAOException, SQLException {
        System.out.println("Enter login: ");
        String login = new Scanner(System.in).nextLine();

        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();

        LoginRequest request = new LoginRequest();
        request.setCommandName("LOGIN");
        request.setLogin(login);
        request.setPassword(password);

        LoginResponse response = (LoginResponse) new Controller().doRequest(request);
        if((!response.isErrorStatus())){
            System.out.println(response.getErrorMessage());
        }
        return response;
    }

    public static void registration() throws ServiceException, DAOException, SQLException {
        System.out.println("Enter name: ");
        String name = new Scanner(System.in).nextLine();

        System.out.println("Enter login: ");
        String login = new Scanner(System.in).nextLine();

        System.out.println("Enter password: ");
        String password = new Scanner(System.in).nextLine();

        System.out.println("Enter rights (admin/user): ");
        String rights = new Scanner(System.in).nextLine();

        RegisterRequest request = new RegisterRequest();
        request.setCommandName("REGISTER");
        request.setName(name);
        request.setLogin(login);
        request.setPassword(password);
        request.setRights(rights);

        Response response = new Controller().doRequest(request);
        if((!response.isErrorStatus())){
            System.out.println(response.getErrorMessage());
        }
    }
}
