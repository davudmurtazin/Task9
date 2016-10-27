package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetSubjectByIDRequest extends Request {
    private int subjectID;

    public GetSubjectByIDRequest() {
    }

    public GetSubjectByIDRequest(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
}
