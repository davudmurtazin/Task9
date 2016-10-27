package by.tc.tester.bean.entity;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class Question {
    private int questionID;
    private int subjectID;
    private String content;
    private String rightAnswer;

    public Question() {
    }

    public Question(int questionID, int subjectID, String content, ArrayList<AnswerOption> options, String rightAnswer) {
        this.questionID = questionID;
        this.subjectID = subjectID;
        this.content = content;
        this.rightAnswer = rightAnswer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionID != question.questionID) return false;
        if (subjectID != question.subjectID) return false;
        if (content != null ? !content.equals(question.content) : question.content != null) return false;
        return rightAnswer != null ? rightAnswer.equals(question.rightAnswer) : question.rightAnswer == null;

    }

    @Override
    public int hashCode() {
        int result = questionID;
        result = 31 * result + subjectID;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (rightAnswer != null ? rightAnswer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "question{" +
                "questionID=" + questionID +
                ", subjectID=" + subjectID +
                ", content='" + content + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }
}
