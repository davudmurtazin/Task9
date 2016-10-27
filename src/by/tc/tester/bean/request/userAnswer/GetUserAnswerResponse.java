package by.tc.tester.bean.request.userAnswer;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.UserAnswer;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetUserAnswerResponse extends Response{
    private ArrayList<UserAnswer> userAnswers = new ArrayList<>();

    public GetUserAnswerResponse() {
    }

    public GetUserAnswerResponse(ArrayList<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public ArrayList<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
