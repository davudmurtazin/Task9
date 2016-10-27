package by.tc.tester.controller.testMenu;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.bean.entity.User;
import by.tc.tester.bean.entity.UserAnswer;
import by.tc.tester.bean.request.answerOption.AddAnswerOptionRequest;
import by.tc.tester.bean.request.question.AddQuestionRequest;
import by.tc.tester.bean.request.question.AddQuestionResponse;
import by.tc.tester.bean.request.question.GetQuestionsRequest;
import by.tc.tester.bean.request.question.GetQuestionsResponse;
import by.tc.tester.bean.request.subject.*;
import by.tc.tester.bean.request.user.ShowUserRequest;
import by.tc.tester.bean.request.user.ShowUserResponse;
import by.tc.tester.bean.request.userAnswer.GetUserAnswerRequest;
import by.tc.tester.bean.request.userAnswer.GetUserAnswerResponse;
import by.tc.tester.command.impl.subject.GetSubjectByID;
import by.tc.tester.command.impl.subject.GetSubjects;
import by.tc.tester.controller.Controller;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.regex.Pattern;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AdminMenu {

    public static void printAddTestText(){
        System.out.println("What would you like to add?: \n" +
                "1. Add subject \n"+
                "2. Add question to subject \n" +
                "3. Add variants to question \n"+
                "0. Back");
    }

    public static void addTestMenu() throws DAOException, ServiceException, SQLException {
        boolean flag = true;
        while(flag){
            printAddTestText();
            String choice = new Scanner(System.in).nextLine();
            switch (choice){
                case "1": addSubject(); break;
                case "2": addQuestion(); break;
                case "3": addAnswerOption(); break;
                case "0": flag = false; break;
                default: System.out.println("Incorrect command!");
            }
        }
    }

    public static int addSubject() throws DAOException, ServiceException, SQLException {
        System.out.println("Enter subject name: ");
        String subjectName = new Scanner(System.in).nextLine();

        AddSubjectRequest request = new AddSubjectRequest();
        request.setCommandName("ADD_NEW_SUBJECT");
        request.setSubjectName(subjectName);

        AddSubjectResponse response = (AddSubjectResponse) new Controller().doRequest(request);
        int subjectID = response.getSubjectID();
        return subjectID;
    }

    public static int addQuestion() throws DAOException, ServiceException, SQLException {
        int subjectID = chooseSubject();

        System.out.println("Enter question: ");
        String content = new Scanner(System.in).nextLine();

        System.out.println("Enter right answer: ");
        String rightAnswer = new Scanner(System.in).nextLine();

        AddQuestionRequest requestQuestion = new AddQuestionRequest();
        requestQuestion.setCommandName("ADD_NEW_QUESTION");
        requestQuestion.setSubjectID(subjectID);
        requestQuestion.setContent(content);
        requestQuestion.setRightAnswer(rightAnswer);
        AddQuestionResponse response = (AddQuestionResponse) new Controller().doRequest(requestQuestion);
        int questionID = response.getQuestionID();

        if ((!response.isErrorStatus())) {
            System.out.println(response.getErrorMessage());
        }
        return questionID;
    }

    public static void addAnswerOption() throws DAOException, ServiceException, SQLException {
        int questionID = chooseQuestion(chooseSubject());
        System.out.println("Enter variant: ");
        String answerOption = new Scanner(System.in).nextLine();

        AddAnswerOptionRequest request = new AddAnswerOptionRequest();
        request.setCommandName("ADD_NEW_ANSWER_OPTION");
        request.setQuestionID(questionID);
        request.setOption(answerOption);
        Response response = new Controller() .doRequest(request);
        if ((!response.isErrorStatus())) {
            System.out.println(response.getErrorMessage());
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
        boolean flag = false;
        int checker = 0;
        while(!flag){
            String subjectChoice = new Scanner(System.in).nextLine();
            if(isRightValue(subjectChoice, subjects.size())){
                int selectedUser = Integer.parseInt(subjectChoice);
                for (int i = 0; i < subjects.size(); i++ ){
                    subjectID = subjects.get(selectedUser - 1).getSubjectID();
                    checker++;
                    break;
                }
            }
            if(checker == 0){
                System.out.println("Please enter number from existing subjects: ");
            }else {
                flag = true;
            }
        }
        return subjectID;
    }

    public static int chooseQuestion(int subjectID) throws DAOException, ServiceException, SQLException {
        System.out.println("This is this list of questions. You can choose a question to which you want to add a variant: ");
        GetQuestionsRequest getQuestionsRequest = new GetQuestionsRequest();
        getQuestionsRequest.setCommandName("GET_QUESTIONS_BY_SUBJECT");
        getQuestionsRequest.setSubjectID(subjectID);
        GetQuestionsResponse getQuestionsResponse = (GetQuestionsResponse) new Controller().doRequest(getQuestionsRequest);
        ArrayList<Question> questions = getQuestionsResponse.getQuestions();
        for (int i = 0; i < questions.size(); i++ ){
            System.out.println((i+1) + ". " + questions.get(i).getContent());
        }
        boolean flag = false;
        int checker = 0;
        int questionID = 0;
        while(!flag){
            String choice = new Scanner(System.in).nextLine();
            if (isRightValue(choice, questions.size())) {
                int selectedQuestion = Integer.parseInt(choice);
                for (int i = 0; i < questions.size(); i++ ){
                        questionID = questions.get(selectedQuestion - 1).getQuestionID();
                        checker++;
                        flag = true;
                        break;
                }
                if(checker == 0){
                    System.out.println("Please enter number of question from existing list: ");
                }
            } else {
                System.out.println("Please enter number of question from existing list: ");
            }

        }
        return questionID;
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

    public static void getUserResults() throws DAOException, ServiceException, SQLException {
        User user = getUser();
        GetUserAnswerRequest request = new GetUserAnswerRequest();
        request.setCommandName("GET_USER_ANSWERS");
        request.setUserID(user.getUserID());

        GetUserAnswerResponse response = (GetUserAnswerResponse) new Controller().doRequest(request);
        ArrayList<UserAnswer> userAnswers = response.getUserAnswers();
        for(int userAnswer = 0; userAnswer < userAnswers.size(); userAnswer++ ){
            System.out.println("'" + user.getName() + "' passed "+ getSubject(userAnswers.get(userAnswer).getSubjectID())+" test with " +
                    userAnswers.get(userAnswer).getRight() + " right answers and had " + userAnswers.get(userAnswer).getMark() + " mark.");
        }
    }

    public static User getUser() throws DAOException, ServiceException, SQLException {
        ShowUserRequest request = new ShowUserRequest();
        request.setCommandName("SHOW_USER");
        request.setRights("user");

        ShowUserResponse response = (ShowUserResponse) new Controller().doRequest(request);
        ArrayList<User> users = response.getUsers();
        System.out.println("Select user: ");
        for(int user = 0; user < users.size(); user++){
            System.out.println((user + 1) + ". " + users.get(user).getName());
        }
        boolean flag = false;
        int checker = 0;
        User user = new User();
        while(!flag){
            String choice = new Scanner(System.in).nextLine();
            if (isRightValue(choice, users.size())) {
                int selectedUser = (Integer.parseInt(choice)-1);
                for (int i = 0; i < users.size(); i++ ){
                        user.setUserID(users.get(selectedUser).getUserID());
                        user.setName(users.get(selectedUser).getName());
                        checker++;
                        flag = true;
                        break;
                }
                if(checker == 0){
                    System.out.println("Please enter number of user from existing list: ");
                }
            } else {
                System.out.println("Please enter number of user from existing list: ");
            }

        }
        return user;
    }

    public static String getSubject(int subjectID) throws DAOException, ServiceException, SQLException {
        GetSubjectByIDRequest request = new GetSubjectByIDRequest();
        request.setCommandName("GET_SUBJECT_BY_ID");
        request.setSubjectID(subjectID);

        GetSubjectByIDResponse response = (GetSubjectByIDResponse) new Controller().doRequest(request);
        String subjectName = response.getSubjectName();
        return subjectName;
    }
}
