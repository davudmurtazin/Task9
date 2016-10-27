package by.tc.tester.bean.request.user;

import by.tc.tester.bean.Response;

/**
 * Created by Davud_Murtazin on 10/16/2016.
 */
public class RegisterResponse extends Response {
    private int userID;
    private  String rights;

    public RegisterResponse() {
    }

    public RegisterResponse(int userID, String rights) {
        this.userID = userID;
        this.rights = rights;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
