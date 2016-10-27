package by.tc.tester.bean.entity;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/25/2016.
 */
public class Subject {
    private int subjectID;
    private String subjectName;

    public Subject() {
    }

    public Subject(int subjectID, String subjectName, ArrayList<Question> questions) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (subjectID != subject.subjectID) return false;
        return subjectName != null ? subjectName.equals(subject.subjectName) : subject.subjectName == null;

    }

    @Override
    public int hashCode() {
        int result = subjectID;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
