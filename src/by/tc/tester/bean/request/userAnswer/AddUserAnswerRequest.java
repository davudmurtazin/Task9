package by.tc.tester.bean.request.userAnswer;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AddUserAnswerRequest extends Request {
    private int userID;
    private int subjectID;
    private int right;
    private double mark;

    public AddUserAnswerRequest() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
