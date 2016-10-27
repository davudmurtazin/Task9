package by.tc.tester.bean.request.answerOption;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetAnswerOptionsRequest extends Request {
    private int questionID;

    public GetAnswerOptionsRequest() {
    }

    public GetAnswerOptionsRequest(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
