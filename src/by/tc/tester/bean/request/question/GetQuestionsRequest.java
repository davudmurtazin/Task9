package by.tc.tester.bean.request.question;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetQuestionsRequest extends Request {
    private int subjectID;

    public GetQuestionsRequest() {
    }

    public GetQuestionsRequest(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
}
