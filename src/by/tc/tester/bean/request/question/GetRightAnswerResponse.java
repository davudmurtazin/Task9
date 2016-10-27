package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetRightAnswerResponse extends Response {
    private int questionID;
    private String rightAnswer;

    public GetRightAnswerResponse() {
    }

    public GetRightAnswerResponse(int questionID, String rightAnswer) {
        this.questionID = questionID;
        this.rightAnswer = rightAnswer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
