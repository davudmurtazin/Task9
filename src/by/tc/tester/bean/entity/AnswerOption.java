package by.tc.tester.bean.entity;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AnswerOption {
    private int answerOptionID;
    private int questionID;
    private String option;

    public AnswerOption() {
    }

    public AnswerOption(int answerOptionID, int questionID, String option) {
        this.answerOptionID = answerOptionID;
        this.questionID = questionID;
        this.option = option;
    }

    public int getAnswerOptionID() {
        return answerOptionID;
    }

    public void setAnswerOptionID(int answerOptionID) {
        this.answerOptionID = answerOptionID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOption that = (AnswerOption) o;

        if (answerOptionID != that.answerOptionID) return false;
        if (questionID != that.questionID) return false;
        return option != null ? option.equals(that.option) : that.option == null;

    }

    @Override
    public int hashCode() {
        int result = answerOptionID;
        result = 31 * result + questionID;
        result = 31 * result + (option != null ? option.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "answerOptionID=" + answerOptionID +
                ", questionID=" + questionID +
                ", option='" + option + '\'' +
                '}';
    }
}
