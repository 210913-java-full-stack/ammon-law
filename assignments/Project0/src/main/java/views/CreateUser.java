package views;

import DAO.AddressDAO;
import DAO.UserDAO;
import models.Address;
import models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateUser extends View{
    public CreateUser(Scanner scanner){
        super("CreateUser", scanner);
    }


    //get the user to input valid first and last name, password, email, and address. Using the user dao, create the user in the
    //database and return the user id. use the user id to make the address
    @Override
    public void renderView() throws SQLException {
        UserDAO dao = new UserDAO(viewManager.getConn());
        AddressDAO addressDAO = new AddressDAO(viewManager.getConn());

        User user = new User(firstName(), lastName(), password(), email());
        String sName = streetName();
        String sNum = streetNum();

        int uID=dao.createGetID(user);

        Address address = new Address(uID, sName, sNum);

        addressDAO.create(address);

        System.out.println("\nEnter selection:\n\n1: Main Menu\n2: Login\nQ: Quit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                viewManager.navigate("MainMenu");
                break;
            case "2":
                viewManager.navigate("Login");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }

    private String firstName()
    {
        String input="";
        boolean run = true;
        while(run)
        {
            boolean valid=true;
            System.out.println("Enter your first name: \n");
            input = scanner.nextLine();
            for(int i=0; i<input.length(); i++){
                if (Character.isDigit(input.charAt(i)))
                    valid=false;
            }
            if(!valid || input=="")
                System.out.println("Not a valid name\n");
            else
                run=false;
        }
        return input;
    }

    private String lastName()
    {
        String input="";
        boolean run = true;
        while(run)
        {
            boolean valid=true;
            System.out.println("Enter your last name: \n");
            input = scanner.nextLine();
            for(int i=0; i<input.length(); i++){
                if (Character.isDigit(input.charAt(i)))
                    valid=false;
            }
            if(!valid || input=="")
                System.out.println("Not a valid name\n");
            else
                run=false;
        }
        return input;
    }

    private String password()
    {
        String input="";
        boolean run = true;
        while(run)
        {
            System.out.println("Enter your password(min length 8): \n");
            input = scanner.nextLine();

            if(input.length()<8)
                System.out.println("Not a valid password\n");
            else
                run=false;
        }
        return input;
    }

    private String streetName()
    {
        String input="";
        boolean run = true;
        while(run) {
            System.out.println("Enter your street name: \n");
            input = scanner.next();

            if (input.length() < 1)
                System.out.println("Not a street name\n");
            else
                run = false;
        }
        return input;
    }

    private String streetNum()
    {
        String input="";
        boolean run = true;
        while(run)
        {
            boolean valid=true;
            System.out.println("Enter your street number: \n");
            input = scanner.next();
            for(int i=0; i<input.length(); i++){
                if (!Character.isDigit(input.charAt(i)))
                    valid=false;
            }
            if(!valid || input=="")
                System.out.println("Not a valid street number\n");
            else
                run=false;
        }
        return input;
    }

    private String email(){
        String input="";
        boolean run = true;
        while(run)
        {
            boolean valid=true, amp=false, dot=false;
            System.out.println("Enter your email: \n");
            input = scanner.next();
            if (input.charAt(0)=='@')
                valid=false;
            for(int i=0; i<input.length(); i++){
                if (input.charAt(i)=='&')
                    amp=true;
                if(amp==true&&input.charAt(i)=='.')
                    dot=true;
            }
            if(!valid || !amp || !dot)
                System.out.println("Not a valid street number\n");
            else
                run=false;
        }
        return input;
    }
}
