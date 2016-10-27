package by.tc.tester.bean.request.user;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/13/2016.
 */
public class RegisterRequest extends Request {
    private String name;
    private String login;
    private String password;
    private String rights;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String login, String password, String rights) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.rights = rights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
