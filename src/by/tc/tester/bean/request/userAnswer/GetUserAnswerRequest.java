package by.tc.tester.bean.request.userAnswer;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetUserAnswerRequest extends Request {
    private int userID;

    public GetUserAnswerRequest() {
    }

    public GetUserAnswerRequest(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
