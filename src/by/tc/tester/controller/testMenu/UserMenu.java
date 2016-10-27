package by.tc.tester.controller.testMenu;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.AnswerOption;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.bean.request.answerOption.GetAnswerOptionsRequest;
import by.tc.tester.bean.request.answerOption.GetAnswerOptionsResponse;
import by.tc.tester.bean.request.question.GetQuestionsRequest;
import by.tc.tester.bean.request.question.GetQuestionsResponse;
import by.tc.tester.bean.request.subject.GetSubjectRequest;
import by.tc.tester.bean.request.subject.GetSubjectsResponse;
import by.tc.tester.bean.request.userAnswer.AddUserAnswerRequest;
import by.tc.tester.controller.Controller;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class UserMenu {
    private static final Controller controller = new Controller();

    public static void chooseTest(int userID) throws DAOException, ServiceException, SQLException {
        System.out.println("Test started!\n");
        int subjectID = chooseSubject();
        ArrayList<Question> questions = getQuestions(subjectID);
        ArrayList<AnswerOption> answerOptions;
        int questionCounter = 0;
        int rightAnswers = 0;
        boolean answerOptionFlag = true;
        if(questions.size() != 0){
            for(int question = 0; question < questions.size(); question++){
                answerOptions = getAnswerOptions(questions.get(question).getQuestionID());
                if(answerOptions.size() !=0){
                    System.out.println(questions.get(question).getContent());
                    for (int answerOption = 0; answerOption < answerOptions.size(); answerOption++ ){
                        System.out.println((answerOption + 1) + ". " + answerOptions.get(answerOption).getOption());
                    }
                    boolean flag = true;
                    while(flag){
                        String userAnswer = new Scanner(System.in).nextLine();
                        if (isRightValue(userAnswer,answerOptions.size())){
                            int selectedAnswerOption = Integer.parseInt(userAnswer);
                            String answerOptionLowerCase = answerOptions.get(selectedAnswerOption - 1).getOption().toLowerCase();
                            String questionRightAnswer = questions.get(question).getRightAnswer().toLowerCase();
                            if(answerOptionLowerCase.equals(questionRightAnswer)){
                                rightAnswers++;
                            }
                            flag = false;
                        }
                        if(rightAnswers == 0){
                            System.out.println("Please enter number from existing subjects: ");
                        }else {
                            flag = false;
                        }
                    }
                }else {
                    System.out.println("It subject test doesn't ready yet!");
                    answerOptionFlag = false;
                }
                questionCounter++;
            }
            if(answerOptionFlag){
                writeToDataBase(userID, subjectID, rightAnswers, getMark(questionCounter, rightAnswers));
                System.out.println("This is you mark: " + getMark(questionCounter, rightAnswers));
            }
        }else {
            System.out.println("It subject test doesn't ready yet!");
        }
    }

    public static int chooseSubject() throws DAOException, ServiceException, SQLException {
        System.out.println("Please, firstly choose a subject: ");
        GetSubjectRequest getSubjectsRequest = new GetSubjectRequest();
        getSubjectsRequest.setCommandName("GET_SUBJECTS");
        GetSubjectsResponse getSubjectsResponse = (GetSubjectsResponse) new Controller().doRequest(getSubjectsRequest);
        ArrayList<Subject> subjects = getSubjectsResponse.getSubjects();
        for (int i = 0; i < subjects.size(); i++ ){
            System.out.println((i + 1) + ". " + subjects.get(i).getSubjectName());
        }
        int subjectID = 0;
        boolean flag = true;
        int checker = 0;
        while(flag){
            String subjectChoice = new Scanner(System.in).nextLine();
            if(isRightValue(subjectChoice, subjects.size())){
                int selectedSubject = (Integer.parseInt(subjectChoice)-1);
                subjectID = subjects.get(selectedSubject).getSubjectID();
                checker++;
            }
            if(checker == 0){
                System.out.println("Please enter number from existing subjects: ");
            }else {
                flag = false;
            }
        }
        return subjectID;
    }

    public static ArrayList<Question> getQuestions(int subjectID) throws DAOException, ServiceException, SQLException {
        GetQuestionsRequest getQuestionsRequest = new GetQuestionsRequest();
        getQuestionsRequest.setCommandName("GET_QUESTIONS_BY_SUBJECT");
        getQuestionsRequest.setSubjectID(subjectID);
        GetQuestionsResponse getQuestionsResponse = (GetQuestionsResponse) new Controller().doRequest(getQuestionsRequest);
        ArrayList<Question> questions = getQuestionsResponse.getQuestions();
        return questions;
    }

    public static ArrayList<AnswerOption> getAnswerOptions(int questionID) throws DAOException, ServiceException, SQLException {
        GetAnswerOptionsRequest request = new GetAnswerOptionsRequest();
        request.setCommandName("GET_ANSWER_OPTIONS");
        request.setQuestionID(questionID);
        GetAnswerOptionsResponse response = (GetAnswerOptionsResponse) new Controller().doRequest(request);
        ArrayList<AnswerOption> answerOptions = response.getAnswerOptions();
        return answerOptions;
    }

    public static boolean isRightValue(String value, int listSize){
        boolean status=true;
        if(value.length()<1)
            return false;
        if(!value.matches("[a-zA-Z]+")){
            if(Double.parseDouble(value) > (double) listSize){
                return false;
            }
        }else return false;

        for(int i = 0;i<value.length();i++){
            char c=value.charAt(i);
            if(Character.isDigit(c) || c=='.'){

            }else{
                status=false;
                break;
            }
        }
        return status;
    }

    public static double getMark(int questionCounter, int rightAnswers){
        double mark = ((double) rightAnswers / (double)questionCounter) * 10;
        DecimalFormat df = new DecimalFormat("#.#");
        mark = Double.valueOf(df.format(mark));
        return mark;
    }

    public static void writeToDataBase(int userID, int subjectID, int rightAnswers, double mark) throws DAOException, ServiceException, SQLException {
        AddUserAnswerRequest request = new AddUserAnswerRequest();
        request.setCommandName("ADD_USER_ANSWER");
        request.setUserID(userID);
        request.setSubjectID(subjectID);
        request.setRight(rightAnswers);
        request.setMark(mark);
        Response response = new Controller().doRequest(request);
    }
}
