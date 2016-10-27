package by.tc.tester.service.impl;

import by.tc.tester.bean.entity.Question;
import by.tc.tester.bean.entity.Subject;
import by.tc.tester.xparser.DOMParserUtil;
import by.tc.tester.xparser.DOMParserXML;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Davud_Murtazin on 10/27/2016.
 */
public class TesterServiceImplTest {
    private DOMParserUtil domParser = new DOMParserUtil();
    private TesterServiceImpl testerService = null;

    @DataProvider(name = "createSubjectID")
    public Object[][] createSubjectID() throws IOException, SAXException {
        return new Object[][]{
                {domParser.parseXML().getSubjects().get(0).getSubjectID(), domParser.parseXML().getSubjects().get(0).getSubjectName()},
                {domParser.parseXML().getSubjects().get(1).getSubjectID(), domParser.parseXML().getSubjects().get(1).getSubjectName()},
                {domParser.parseXML().getSubjects().get(2).getSubjectID(), domParser.parseXML().getSubjects().get(2).getSubjectName()}};
    }

    @Test(dataProvider = "createSubjectID")
    public void testGetSubjectsByID(int subjectID, String subjectName) throws Exception {
        testerService = new TesterServiceImpl();
        Assert.assertEquals(subjectName, testerService.getSubjectsByID(subjectID));
    }

    @Test
    public void testGetSubjects() throws Exception {
        testerService = new TesterServiceImpl();
        Assert.assertEquals(domParser.parseXML().getSubjects(), testerService.getSubjects());
    }
}