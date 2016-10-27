package by.tc.tester.bean.request.subject;

import by.tc.tester.bean.Request;
import by.tc.tester.bean.entity.Question;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class AddSubjectRequest extends Request {
    private String subjectName;

    public AddSubjectRequest() {
    }

    public AddSubjectRequest( String subjectName, ArrayList<Question> questions) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
