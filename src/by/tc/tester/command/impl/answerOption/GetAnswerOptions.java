package by.tc.tester.command.impl.answerOption;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.AnswerOption;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.request.answerOption.GetAnswerOptionsRequest;
import by.tc.tester.bean.request.answerOption.GetAnswerOptionsResponse;
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
public class GetAnswerOptions implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof GetAnswerOptionsRequest) {
            int questionID = ((GetAnswerOptionsRequest) request).getQuestionID();

            ServiceFactory service = ServiceFactory.getInstance();
            TestService testService = service.getTestService();

            GetAnswerOptionsResponse response = new GetAnswerOptionsResponse();
            response.setAnswerOptions(testService.getAnswerOptions(questionID));
            response.setErrorStatus(false);
            response.setResultMessage("Answer option got!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
