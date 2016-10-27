package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.Question;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetQuestionsResponse extends Response {
    private ArrayList<Question> questions = new ArrayList<>();

    public GetQuestionsResponse() {
    }

    public GetQuestionsResponse(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
