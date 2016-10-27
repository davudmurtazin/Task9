package by.tc.tester.bean.request.user;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/13/2016.
 */
public class LoginRequest extends Request {
    private int userID;
    private String login;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(int userID, String login, String password) {
        this.userID = userID;
        this.login = login;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
