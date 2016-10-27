package by.tc.tester.bean.request.userAnswer;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class UserAnswerResponse extends Response{
    private int userAnswerID;

    public UserAnswerResponse() {
    }

    public UserAnswerResponse(int userAnswerID) {
        this.userAnswerID = userAnswerID;
    }

    public int getUserAnswerID() {
        return userAnswerID;
    }

    public void setUserAnswerID(int userAnswerID) {
        this.userAnswerID = userAnswerID;
    }
}
