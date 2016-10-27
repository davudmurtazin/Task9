package by.tc.tester.xparser;

import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParserUtil {
    private Question question = null;
    private Subject subject = null;
    private List<Question> questions = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private DOMParserXML domParserXML = new DOMParserXML();

    public DOMParserXML parseXML() throws IOException, SAXException {
        DOMParser domParser = new DOMParser();
        domParser.parse("src/data.xml");
        Document document = domParser.getDocument();
        Element element = document.getDocumentElement();

        NodeList dataSelectionList = element.getElementsByTagName("dataSelection");

        for(int dataSelectionCounter = 0; dataSelectionCounter < dataSelectionList.getLength(); dataSelectionCounter++){
            Element dataSelectionElement = (Element) dataSelectionList.item(dataSelectionCounter);
            if(dataSelectionElement.getAttribute("type").equals("questionType")){
                NodeList questionList = element.getElementsByTagName("question");
                for(int questionCounter = 0; questionCounter < questionList.getLength(); questionCounter++){
                    Element questionElement = (Element) questionList.item(questionCounter);
                    question = new Question();
                    question.setQuestionID(Integer.parseInt(getChild(questionElement, "questionID").getTextContent()));
                    question.setSubjectID(Integer.parseInt(getChild(questionElement, "questionSubjectID").getTextContent()));
                    question.setContent(getChild(questionElement, "content").getTextContent());
                    question.setRightAnswer(getChild(questionElement, "rightAnswer").getTextContent());
                    questions.add(question);
                }
                domParserXML.setQuestions(questions);
            }else if(dataSelectionElement.getAttribute("type").equals("subjectType")){
                NodeList subjectList = element.getElementsByTagName("subject");
                for(int subjectCounter = 0; subjectCounter < subjectList.getLength(); subjectCounter++){
                    Element subjectElement = (Element) subjectList.item(subjectCounter);
                    subject = new Subject();
                    subject.setSubjectID(Integer.parseInt(getChild(subjectElement, "subjectID").getTextContent()));
                    subject.setSubjectName(getChild(subjectElement, "subjectName").getTextContent());
                    subjects.add(subject);
                }
                domParserXML.setSubjects(subjects);
            }
        }
        return domParserXML;
    }

    public Element getChild(Element element, String childElement){
        NodeList dishList = element.getElementsByTagName(childElement);
        Element elementChild = (Element)dishList.item(0);
        return elementChild;
    }
}