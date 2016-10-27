package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetRightAnswerRequest extends Request {
    private int questionID;

    public GetRightAnswerRequest() {
    }

    public GetRightAnswerRequest(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
