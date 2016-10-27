package by.tc.tester.xparser;

import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;

import java.util.List;

/**
 * Created by Davud_Murtazin on 10/27/2016.
 */
public class DOMParserXML {
    private List<Question> questions = null;
    private List<Subject> subjects = null;

    public DOMParserXML() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
