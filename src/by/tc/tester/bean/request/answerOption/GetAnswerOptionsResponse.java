package by.tc.tester.bean.request.answerOption;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.AnswerOption;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetAnswerOptionsResponse extends Response {
    private ArrayList<AnswerOption> answerOptions = new ArrayList<>();

    public GetAnswerOptionsResponse() {
    }

    public GetAnswerOptionsResponse(ArrayList<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public ArrayList<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(ArrayList<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
