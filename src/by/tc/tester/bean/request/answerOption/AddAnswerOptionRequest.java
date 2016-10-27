package by.tc.tester.bean.request.answerOption;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AddAnswerOptionRequest extends Request {
    private int questionID;
    private String option;

    public AddAnswerOptionRequest() {
    }

    public AddAnswerOptionRequest(int questionID, String option) {
        this.questionID = questionID;
        this.option = option;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
