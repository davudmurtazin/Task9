package by.tc.tester.dao.impl.mysql;

import by.tc.tester.bean.entity.*;
import by.tc.tester.dao.TestDAO;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.dao.impl.pool.AbstractDAO;
import by.tc.tester.service.exception.ServiceException;
import org.testng.internal.TestResult;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class MySQLTestDAO extends AbstractDAO implements TestDAO {
    public static final String INSERT_NEW_ANSWER_OPTION =
            "INSERT INTO `answeroption` (QuestionID, Options) VALUES (?, ?)";
    public static final String INSERT_NEW_QUESTION =
            "INSERT INTO `question` (SubjectID, Content, RightAnswer) VALUES (?, ?, ?)";
    public static final String INSERT_NEW_SUBJECT =
            "INSERT INTO `subject` (SubjectName) VALUES (?)";
    public static final String INSERT_USER_ANSWER =
            "INSERT INTO `user_answer` (UserID, SubjectID, RightAnswers, UserMark) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ANSWER_OPTIONS_BY_QUESTION_ID =
            "SELECT * FROM `answeroption` WHERE QuestionID = ?;";
    public static final String SELECT_QUESTIONS_BY_SUBJECT_ID =
            "SELECT * FROM `question` WHERE SubjectID = ?;";
    public static final String SELECT_QUESTION_BY_RIGHT_ANSWER =
            "SELECT * FROM `question` WHERE RightAnswer = ?;";
    public static final String SELECT_SUBJECT =
            "SELECT * FROM `subject`;";
    public static final String SELECT_SUBJECT_BY_NAME =
            "SELECT * FROM `subject` WHERE SubjectName = ?;";
    public static final String SELECT_SUBJECT_BY_ID =
            "SELECT * FROM `subject` WHERE SubjectID = ?;";
    public static final String SELECT_USER_ANSWERS_BY_USER_ID =
            "SELECT * FROM `user_answer` WHERE UserID = ?;";

    @Override
    public void addNewAnswerOption(int questionID, String Option) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_ANSWER_OPTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, questionID);
            preparedStatement.setString(2, Option);
            preparedStatement.executeUpdate();
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Couldn't insert answer option to database!");
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public ArrayList<AnswerOption> getAnswerOptions(int questionID) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<AnswerOption> answerOptions = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ANSWER_OPTIONS_BY_QUESTION_ID);
            preparedStatement.setInt(1, questionID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setAnswerOptionID(resultSet.getInt("AnswerOptionID"));
                answerOption.setQuestionID(resultSet.getInt("QuestionID"));
                answerOption.setOption(resultSet.getString("Options"));
                answerOptions.add(answerOption);
            }
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return answerOptions;
    }

    @Override
    public int addNewQuestion(int subjectID, String content, String rightAnswer) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int questionID = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_QUESTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, subjectID);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, rightAnswer);
            preparedStatement.executeUpdate();

            String query = "SELECT QuestionID FROM question WHERE SubjectID = '" + subjectID + "' AND "
                    + "Content = '" + content + "'" + " AND "
                    + "RightAnswer = '" + rightAnswer + "';";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    questionID = rs.getInt(1);
                }
            }catch (SQLException e){
                    throw new DAOException(e.getMessage());
            }
        } catch (DAOException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return questionID;
    }

    @Override
    public ArrayList<Question> getQuestion(int subjectID) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Question> questions = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_QUESTIONS_BY_SUBJECT_ID);
            preparedStatement.setInt(1, subjectID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Question question = new Question();
                question.setQuestionID(resultSet.getInt("QuestionID"));
                question.setSubjectID(resultSet.getInt("SubjectID"));
                question.setContent(resultSet.getString("Content"));
                question.setRightAnswer(resultSet.getString("RightAnswer"));
                questions.add(question);
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return questions;
    }

    @Override
    public int addNewSubject(String subjectName) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int subjectID = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW_SUBJECT);
            preparedStatement.setString(1, subjectName);
            preparedStatement.executeUpdate();
            String query = "SELECT SubjectID FROM subject WHERE SubjectName = '" + subjectName + "';";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    subjectID = rs.getInt(1);
                }
            }catch (SQLException e){
                throw new DAOException(e.getMessage());
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return subjectID;
    }

    @Override
    public ArrayList<Subject> getSubjects() throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SUBJECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(resultSet.getInt("SubjectID"));
                subject.setSubjectName(resultSet.getString("SubjectName"));
                subjects.add(subject);
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return subjects;
    }

    @Override
    public ArrayList<Subject> getSubjectsByName(String subjectName) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_NAME);
            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(resultSet.getInt("SubjectID"));
                subject.setSubjectName(resultSet.getString("SubjectName"));
                subjects.add(subject);
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return subjects;
    }

    @Override
    public String getSubjectsByID(int subjectID) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String subjectName = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID);
            preparedStatement.setInt(1, subjectID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                subjectName = resultSet.getString("SubjectName");
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return subjectName;
    }

    @Override
    public int addUserAnswer(int userID, int subjectID, int right, double mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int userAnswerID = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_ANSWER);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, subjectID);
            preparedStatement.setInt(3, right);
            preparedStatement.setDouble(4, mark);

            preparedStatement.executeUpdate();

            String query = "SELECT UserAnswerID FROM user_answer WHERE UserID = '" + userID + "';";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    userAnswerID = rs.getInt(1);
                }
            }catch (SQLException e){
                throw new DAOException(e.getMessage());
            }
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return userAnswerID;
    }

    @Override
    public ArrayList<UserAnswer> getUserAnswers(int userID) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<UserAnswer> userAnswers = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_ANSWERS_BY_USER_ID);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setUserAnswerID(resultSet.getInt("UserAnswerID"));
                userAnswer.setUserID(resultSet.getInt("UserID"));
                userAnswer.setSubjectID(resultSet.getInt("SubjectID"));
                userAnswer.setRight(resultSet.getInt("RightAnswers"));
                userAnswer.setMark(resultSet.getDouble("UserMark"));
                userAnswers.add(userAnswer);
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return userAnswers;
    }

    @Override
    public String getRightAnswer(int questionID) throws ServiceException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String userAnswer = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_QUESTION_BY_RIGHT_ANSWER);
            preparedStatement.setInt(1, questionID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                userAnswer = resultSet.getString("RightAnswer");
            }
        } catch (DAOException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return userAnswer;
    }
}
