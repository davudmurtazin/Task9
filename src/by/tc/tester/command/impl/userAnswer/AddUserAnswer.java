package by.tc.tester.command.impl.userAnswer;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.UserAnswer;
import by.tc.tester.bean.request.userAnswer.AddUserAnswerRequest;
import by.tc.tester.bean.request.userAnswer.UserAnswerResponse;
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
public class AddUserAnswer implements Command{
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        AddUserAnswerRequest req;

        if(request instanceof AddUserAnswerRequest){
            req = (AddUserAnswerRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setSubjectID(req.getSubjectID());
        userAnswer.setUserID(req.getUserID());
        userAnswer.setRight(req.getRight());
        userAnswer.setMark(req.getMark());

        ServiceFactory service = ServiceFactory.getInstance();
        TestService testService = service.getTestService();

        UserAnswerResponse response = new UserAnswerResponse();
        try{
            response.setUserAnswerID(testService.addUserAnswer(userAnswer));
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }
        response.setErrorStatus(false);
        response.setResultMessage("User answer added!");

        return response;
    }
}
