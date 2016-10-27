package by.tc.tester.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davud_Murtazin on 10/4/2016.
 */
public class CommandNameValidator {
    private static List<String> commands = new ArrayList<String>(){
        {
        this.add("ADD_NEW_NOTE");
        this.add("FIND_NOTE_BY_CONTENT");
        this.add("FIND_NOTE_BY_DATE");
        this.add("SHOW_NOTES");
        this.add("CLEAR_NOTEBOOK");
        this.add("WRITE_NOTES_TO_FILE");
        this.add("READ_NOTES_FROM_FILE");
        }
    };

    public static boolean isValidCommandName(String commandName)
    {
        boolean flag = false;
        if (commandName!=null || !commandName.equals("") || commands.contains(commandName)){
            flag = true;
        }
        return flag;
    }
}
