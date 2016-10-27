package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class AddSubjectResponse extends Response {
    private int subjectID;

    public AddSubjectResponse() {
    }

    public AddSubjectResponse(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
}
