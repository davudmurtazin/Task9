package by.tc.tester.command.impl.question;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.question.GetQuestionsRequest;
import by.tc.tester.bean.request.question.GetQuestionsResponse;
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
public class GetQuestions implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof GetQuestionsRequest) {
            int subjectID = ((GetQuestionsRequest) request).getSubjectID();

            ServiceFactory service = ServiceFactory.getInstance();
            TestService testService = service.getTestService();

            GetQuestionsResponse response = new GetQuestionsResponse();
            response.setQuestions(testService.getQuestion(subjectID));
            response.setErrorStatus(false);
            response.setResultMessage("Question got!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
