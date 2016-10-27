package by.tc.tester.service.impl;

import by.tc.tester.bean.entity.AnswerOption;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.bean.entity.UserAnswer;
import by.tc.tester.dao.FactoryDAO;
import by.tc.tester.dao.TestDAO;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.TestService;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class TesterServiceImpl implements TestService {

    @Override
    public void addNewAnswerOption(AnswerOption answerOption) throws ServiceException, DAOException {
        if(answerOption.getQuestionID() == 0 || answerOption.getOption().isEmpty()){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        testDAO.addNewAnswerOption(answerOption.getQuestionID(), answerOption.getOption());
    }

    @Override
    public ArrayList<AnswerOption> getAnswerOptions(int questionID) throws ServiceException, DAOException {
        if(questionID == 0){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        ArrayList<AnswerOption> answerOptions = testDAO.getAnswerOptions(questionID);
        return answerOptions;
    }

    @Override
    public int addNewQuestion(Question question) throws ServiceException, DAOException, SQLException {
        if(question.getSubjectID() == 0 || question.getContent().isEmpty()){
            throw new ServiceException("Wrong params!");
        }

        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        int questionID = testDAO.addNewQuestion(question.getSubjectID(), question.getContent(), question.getRightAnswer());
        return questionID;
    }

    @Override
    public ArrayList<Question> getQuestion(int subjectID) throws ServiceException, DAOException {
        if(subjectID == 0){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        ArrayList<Question> questions = testDAO.getQuestion(subjectID);
        return questions;
    }

    @Override
    public int addNewSubject(Subject subject) throws ServiceException, DAOException {
        if (subject.getSubjectName().isEmpty()){
            throw new ServiceException("Wrong param!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        int subjectID = testDAO.addNewSubject(subject.getSubjectName());
        return subjectID;
    }

    @Override
    public ArrayList<Subject> getSubjects() throws ServiceException, DAOException {
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        ArrayList<Subject> subjects = testDAO.getSubjects();
        return subjects;
    }

    @Override
    public ArrayList<Subject> getSubjectsByName(String subjectName) throws ServiceException, DAOException {
        if (subjectName.isEmpty()){
            throw new ServiceException("Wrong param!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        ArrayList<Subject> subjects = testDAO.getSubjectsByName(subjectName);
        return subjects;
    }

    @Override
    public int addUserAnswer(UserAnswer userAnswer) throws ServiceException, DAOException {
        if(userAnswer.getUserID() == 0 || userAnswer.getSubjectID() == 0 || userAnswer.getRight() == 0 || userAnswer.getMark() == 0){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        int userAnswerID = testDAO.addUserAnswer(userAnswer.getUserID(), userAnswer.getSubjectID(), userAnswer.getRight(), userAnswer.getMark());
        return userAnswerID;
    }

    @Override
    public ArrayList<UserAnswer> getUserAnswers(int userID) throws ServiceException, DAOException {
        if(userID == 0){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        ArrayList<UserAnswer> userAnswers = testDAO.getUserAnswers(userID);
        return userAnswers;
    }

    @Override
    public String getRightAnswer(int questionID) throws ServiceException, DAOException {
        if(questionID == 0){
            throw new ServiceException("Wrong params!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        String rightAnswer = testDAO.getRightAnswer(questionID);
        return rightAnswer;
    }

    @Override
    public String getSubjectsByID(int subjectID) throws ServiceException, DAOException {
        if (subjectID == 0){
            throw new ServiceException("Wrong param!");
        }
        TestDAO testDAO = FactoryDAO.getInstance().getTestDAO();
        String subject = testDAO.getSubjectsByID(subjectID);
        return subject;
    }
}
