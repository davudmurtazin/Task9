package by.tc.tester.command.impl.subject;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.bean.request.subject.AddSubjectRequest;
import by.tc.tester.bean.request.subject.AddSubjectResponse;
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
public class AddNewSubject implements Command{
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        AddSubjectRequest req;

        if(request instanceof AddSubjectRequest){
            req = (AddSubjectRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        Subject subject = new Subject();
        subject.setSubjectName(req.getSubjectName());

        ServiceFactory service = ServiceFactory.getInstance();
        TestService testService = service.getTestService();

        AddSubjectResponse response = new AddSubjectResponse();
        try{
            response.setSubjectID(testService.addNewSubject(subject));
        } catch (ServiceException e) {
            throw new CommandException("Wrong command!");
        }
        response.setErrorStatus(false);
        response.setResultMessage("Subject added!");

        return response;
    }
}
