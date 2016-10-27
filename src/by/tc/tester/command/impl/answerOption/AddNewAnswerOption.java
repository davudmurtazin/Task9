package by.tc.tester.command.impl.answerOption;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.AnswerOption;
import by.tc.tester.bean.request.answerOption.AddAnswerOptionRequest;
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
public class AddNewAnswerOption implements Command {

    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        AddAnswerOptionRequest req;

        if(request instanceof AddAnswerOptionRequest){
            req = (AddAnswerOptionRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }
        AnswerOption answerOption = new AnswerOption();
        answerOption.setQuestionID(req.getQuestionID());
        answerOption.setOption(req.getOption());

        ServiceFactory service = ServiceFactory.getInstance();
        TestService testService = service.getTestService();

        try{
            testService.addNewAnswerOption(answerOption);
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }
        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Answer option added!");

        return response;
    }
}
