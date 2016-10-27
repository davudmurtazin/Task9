package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class AddQuestionResponse extends Response {
    private int questionID;

    public AddQuestionResponse() {
    }

    public AddQuestionResponse(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
