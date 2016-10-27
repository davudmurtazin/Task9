package by.tc.tester.bean.request.user;

import by.tc.tester.bean.Response;
import by.tc.tester.bean.entity.User;

import java.util.ArrayList;

/**
 * Created by Davud_Murtazin on 10/26/2016.
 */
public class ShowUserResponse extends Response {
    private ArrayList<User> users = new ArrayList<>();

    public ShowUserResponse() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
