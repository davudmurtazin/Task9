package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.Subject;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class GetSubjectByNameResponse extends Response {
    private ArrayList<Subject> subjects = new ArrayList<>();

    public GetSubjectByNameResponse() {
    }

    public GetSubjectByNameResponse(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
