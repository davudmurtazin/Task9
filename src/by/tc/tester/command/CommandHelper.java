package by.tc.tester.command;

import by.tc.tester.command.impl.question.AddNewQuestion;
import by.tc.tester.command.impl.question.GetQuestions;
import by.tc.tester.command.impl.subject.AddNewSubject;
import by.tc.tester.command.impl.subject.GetSubjectByID;
import by.tc.tester.command.impl.subject.GetSubjectByName;
import by.tc.tester.command.impl.subject.GetSubjects;
import by.tc.tester.command.impl.answerOption.*;
import by.tc.tester.command.impl.user.ShowUser;
import by.tc.tester.command.impl.userAnswer.AddUserAnswer;
import by.tc.tester.command.impl.userAnswer.GetUserAnswers;
import by.tc.tester.command.impl.user.Login;
import by.tc.tester.command.impl.user.Register;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {
		commands.put("LOGIN", new Login());
		commands.put("REGISTER", new Register());
		commands.put("ADD_NEW_SUBJECT", new AddNewSubject());
		commands.put("GET_SUBJECTS", new GetSubjects());
		commands.put("GET_SUBJECTS_BY_NAME", new GetSubjectByName());
		commands.put("GET_SUBJECT_BY_ID", new GetSubjectByID());
		commands.put("ADD_NEW_QUESTION", new AddNewQuestion());
		commands.put("GET_QUESTIONS_BY_SUBJECT", new GetQuestions());
		commands.put("ADD_NEW_ANSWER_OPTION", new AddNewAnswerOption());
		commands.put("GET_ANSWER_OPTIONS", new GetAnswerOptions());
		commands.put("ADD_USER_ANSWER", new AddUserAnswer());
		commands.put("GET_USER_ANSWERS", new GetUserAnswers());
		commands.put("SHOW_USER", new ShowUser());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;
	}
}
