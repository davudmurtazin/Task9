package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetSubjectByIDResponse extends Response {
    private String subjectName;

    public GetSubjectByIDResponse() {
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
