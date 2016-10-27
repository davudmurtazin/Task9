package by.tc.tester.command.impl.userAnswer;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.userAnswer.GetUserAnswerRequest;
import by.tc.tester.bean.request.userAnswer.GetUserAnswerResponse;
import by.tc.tester.command.Command;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.ServiceFactory;
import by.tc.tester.service.TestService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetUserAnswers implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof GetUserAnswerRequest) {
            int userID = ((GetUserAnswerRequest) request).getUserID();

            ServiceFactory service = ServiceFactory.getInstance();
            TestService testService = service.getTestService();

            GetUserAnswerResponse response = new GetUserAnswerResponse();
            response.setUserAnswers(testService.getUserAnswers(userID));
            response.setErrorStatus(false);
            response.setResultMessage("User answer got!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
