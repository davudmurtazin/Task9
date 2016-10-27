package by.tc.tester.command.impl.question;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.request.question.AddQuestionRequest;
import by.tc.tester.bean.request.question.AddQuestionResponse;
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
public class AddNewQuestion implements Command {

    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        AddQuestionRequest req;

        if(request instanceof AddQuestionRequest){
            req = (AddQuestionRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        Question question = new Question();
        question.setSubjectID(req.getSubjectID());
        question.setContent(req.getContent());
        question.setRightAnswer(req.getRightAnswer());

        ServiceFactory service = ServiceFactory.getInstance();
        TestService testService = service.getTestService();

        AddQuestionResponse response = new AddQuestionResponse();
        try{
            response.setQuestionID(testService.addNewQuestion(question));
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }

        response.setErrorStatus(false);
        response.setResultMessage("Question added!");

        return response;
    }
}
