package by.tc.tester.controller.authorization;

import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.controller.testMenu.AdminMenu;
import by.tc.tester.controller.testMenu.UserMenu;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Davud_Murtazin on 10/4/2016.
 */
public class AuthorizationMenu {
    private static final Scanner sc = new Scanner(System.in);

    private static boolean flag = true;

    private void getAuthMenu(){
        System.out.println("Welcome to testing!=)\n" +
                "Please enter to: \n"+
                "1. Login \n" +
                "2. Registration \n" +
                "0. Exit");
    }

    private void getAdminMenu(){
        System.out.println(
                "Please enter to: \n"+
                "1. Add data \n" +
                "2. Get users data, that passed test \n" +
                "0. Logout\n");
    }

    private void getUserMenu(){
        System.out.println(
                "Please enter to: \n"+
                "1. Choose answerOption \n" +
                "0. Logout \n");
    }

    public void testMenu(int userID, String rights) throws ServiceException, DAOException, SQLException {
        if(rights.contentEquals("admin")){
            while(flag){
                getAdminMenu();
                String choice = sc.nextLine();
                switch(choice){
                    case "1": AdminMenu.addTestMenu(); break;
                    case "2": AdminMenu.getUserResults(); break;
                    case "0": authMenu();
                    default: System.out.println("Incorrect command! Enter again: "); break;
                }
            }
        }else if(rights.contentEquals("user")){
            while(flag){
                getUserMenu();
                String choice = sc.nextLine();
                switch(choice){
                    case "1": UserMenu.chooseTest(userID); break;
                    case "0": authMenu();
                    default: System.out.println("Incorrect command! Enter again: "); break;
                }
            }
        }

    }

    public void authMenu() throws ServiceException, DAOException, SQLException {
        while(flag){
            getAuthMenu();
            String choice = sc.nextLine();
            switch(choice){
                case "1":
                    LoginResponse loginResponse = AuthorizationMenuUtil.logination();
                    int userID = loginResponse.getUserID();
                    String rights = loginResponse.getRights();
                    if(userID != 0 && !rights.isEmpty()){
                    testMenu(userID, rights);
                } break;
                case "2": AuthorizationMenuUtil.registration(); break;
                case "0": flag = false;
                default: System.out.println("Incorrect command! Enter again: "); break;
            }
        }
    }



}
