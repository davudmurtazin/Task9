package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.entity.AnswerOption;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AddQuestionRequest extends Request{
    private int subjectID;
    private String content;
    private String rightAnswer;

    public AddQuestionRequest() {
    }

    public AddQuestionRequest(int subjectID, String content, String rightAnswer) {
        this.subjectID = subjectID;
        this.content = content;
        this.rightAnswer = rightAnswer;
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
}
