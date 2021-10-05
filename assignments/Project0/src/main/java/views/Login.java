package views;

import DAO.UserDAO;
import MyArrayList.MyArrayList;
import models.User;
import utils.ViewManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Login extends View{

    public Login(Scanner scanner){
        super("Login", scanner);
    }

    @Override
    public void renderView() throws SQLException {
        UserDAO dao = new UserDAO(viewManager.getConn());
        MyArrayList<User> userList = dao.getAll();

        int userID = userID(userList);

        boolean saveUser = password(userList.get(userID-1));

        if(saveUser){
            viewManager.setCurrentUser(userList.get(userID-1));

            viewManager.navigate("UserMenu");
        }

        else{
            System.out.println("Where to go?:\n\n1: Stay here\n2: Main Menu\n3: Create User\nQ: Quit");
            String input = scanner.nextLine();
            switch(input){
                case "1":
                    viewManager.navigate("Login");
                    break;
                case "2":
                    viewManager.navigate("MainMenu");
                    break;
                case "3":
                    viewManager.navigate("CreateUser");
                    break;
                case"Q":
                case "q":
                    viewManager.setRunning(false);
                    break;
            }
        }


    }

    private int userID(MyArrayList<User> userList)
    {
        String input;
        int id=-1;
        boolean run = true;
        while(run)
        {
            boolean valid=true;
            System.out.println("Enter your user ID: \n");
            input = scanner.nextLine();
            for(int i=0; i<input.length(); i++){
                if (!Character.isDigit(input.charAt(i)))
                    valid=false;
            }
            id=Integer.parseInt(input);
            if(!valid || input=="" || userList.size()<id || id<1)
                System.out.println("Not a valid user ID\n");
            else
                run=false;

        }
        return id;
    }

    private boolean password(User user)
    {
        String input;
        boolean run = true;
        boolean valid=false;
        while(run) {
            System.out.println(user.getID()+" "+user.getPassword());
            System.out.println("Enter your password: \n");
            input = scanner.next();

            if (!input.equals(user.getPassword())){
                System.out.println("Invalid password\n");
                System.out.println("Try Again? Press Y to continue: \n");
                input = scanner.next();
                if (input!="y" && input!="Y")
                    run=false;
            }
            else{
                valid=true;
                run=false;
            }
        }
        return valid;
    }
}
