package by.tc.tester.bean.request.user;

import by.tc.tester.bean.Request;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class ShowUserRequest extends Request {
    private String rights;

    public ShowUserRequest() {
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
