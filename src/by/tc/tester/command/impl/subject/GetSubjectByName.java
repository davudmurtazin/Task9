package by.tc.tester.command.impl.subject;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.Response;
import by.tc.tester.bean.request.subject.GetSubjectByNameResponse;
import by.tc.tester.bean.request.subject.GetSubjectByNameRequest;
import by.tc.tester.command.Command;
import by.tc.tester.command.exception.CommandException;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.ServiceFactory;
import by.tc.tester.service.TestService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetSubjectByName implements Command {
    @Override
    public Response execute(Request request) throws CommandException, ServiceException, DAOException, SQLException {
        if(request instanceof GetSubjectByNameRequest) {

            ServiceFactory service = ServiceFactory.getInstance();
            TestService testService = service.getTestService();

            GetSubjectByNameResponse response = new GetSubjectByNameResponse();
            response.setSubjects(testService.getSubjectsByName(((GetSubjectByNameRequest) request).getSubjectName()));
            response.setErrorStatus(false);
            response.setResultMessage("Got all subjects!");

            return response;
        } else {
            throw new CommandException("Wrong request!");
        }
    }
}
