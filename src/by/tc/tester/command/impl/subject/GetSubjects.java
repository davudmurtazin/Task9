package by.tc.tester.command.impl.subject;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.subject.GetSubjectRequest;
import by.tc.tester.bean.request.subject.GetSubjectsResponse;
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
public class GetSubjects implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof GetSubjectRequest) {

            ServiceFactory service = ServiceFactory.getInstance();
            TestService testService = service.getTestService();

            GetSubjectsResponse response = new GetSubjectsResponse();
            response.setSubjects(testService.getSubjects());
            response.setErrorStatus(false);
            response.setResultMessage("Got all subjects!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
