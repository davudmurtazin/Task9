package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class GetSubjectByNameRequest extends Request {
    private String subjectName;

    public GetSubjectByNameRequest() {
    }

    public GetSubjectByNameRequest(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
