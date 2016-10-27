package by.tc.tester.service;

import by.tc.tester.bean.entity.AnswerOption;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.bean.entity.UserAnswer;
import by.tc.tester.bean.request.question.GetRightAnswerResponse;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public interface TestService {

    void addNewAnswerOption(AnswerOption answerOption) throws ServiceException, DAOException;

    ArrayList<AnswerOption> getAnswerOptions(int questionID) throws ServiceException, DAOException;

    int addNewQuestion(Question question) throws ServiceException, DAOException, SQLException;

    ArrayList<Question> getQuestion(int subjectID)throws ServiceException, DAOException;

    int addNewSubject(Subject subject) throws ServiceException, DAOException;

    ArrayList<Subject> getSubjects() throws ServiceException, DAOException;

    ArrayList<Subject> getSubjectsByName(String subjectName) throws ServiceException, DAOException;

    int addUserAnswer(UserAnswer userAnswer) throws ServiceException, DAOException;

    ArrayList<UserAnswer> getUserAnswers(int userID) throws ServiceException, DAOException;

    String getRightAnswer(int questionID) throws ServiceException, DAOException;

    String getSubjectsByID(int subjectID) throws ServiceException, DAOException;
}
