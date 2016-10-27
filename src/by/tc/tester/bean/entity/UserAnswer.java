package by.tc.tester.bean.entity;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class UserAnswer {
    private int userAnswerID;
    private int userID;
    private int subjectID;
    private int right;
    private double mark;

    public UserAnswer() {
    }

    public UserAnswer(int userAnswerID, int userID, int subjectID, int right, double mark) {
        this.userAnswerID = userAnswerID;
        this.userID = userID;
        this.subjectID = subjectID;
        this.right = right;
        this.mark = mark;
    }

    public int getUserAnswerID() {
        return userAnswerID;
    }

    public void setUserAnswerID(int userAnswerID) {
        this.userAnswerID = userAnswerID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAnswer that = (UserAnswer) o;

        if (userAnswerID != that.userAnswerID) return false;
        if (userID != that.userID) return false;
        if (subjectID != that.subjectID) return false;
        if (right != that.right) return false;
        return Double.compare(that.mark, mark) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userAnswerID;
        result = 31 * result + userID;
        result = 31 * result + subjectID;
        result = 31 * result + right;
        temp = Double.doubleToLongBits(mark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "userAnswerID=" + userAnswerID +
                ", userID=" + userID +
                ", subjectID=" + subjectID +
                ", right=" + right +
                ", mark=" + mark +
                '}';
    }
}
