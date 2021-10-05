package views;

import DAO.AddressDAO;
import DAO.UserDAO;
import MyArrayList.MyArrayList;
import models.Address;
import models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserSettings extends View{
    UserDAO userDAO = new UserDAO(viewManager.getConn());
    AddressDAO addressDAO = new AddressDAO(viewManager.getConn());

    public UserSettings(Scanner scanner){
        super("UserSettings",scanner);
    }

    //get the user addresses and current user and show off that data. After that give the user the option to change any
    //of it and save it or return to the user menu
    @Override
    public void renderView() throws SQLException {
        MyArrayList<Address> userAddresses = addressDAO.getByUserID(viewManager.getCurrentUser().getID());
        User user = viewManager.getCurrentUser();
        System.out.println("First name: "+user.getfName()+"\nLast name: "+user.getlName()+"\nAddresses:\n");
        for(int i=0; i<userAddresses.size(); i++)
        {
            System.out.println(userAddresses.get(i).getStreetName()+" "+userAddresses.get(i).getStreetNum()+"\n");
        }

        System.out.println("Change User Info:\n\n1: First Name\n2: Last Name\n3: Address\n4: Back to User Menu\nQ: Quit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                changeFName();
                userDAO.save(viewManager.getCurrentUser());
                break;
            case "2":
                changeLName();
                userDAO.save(viewManager.getCurrentUser());
                break;
            case "3":
                pickAddress(userAddresses);
                break;
            case "4":
                viewManager.navigate("UserMenu");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }

    //change the current user first name
    private void changeFName(){
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
        viewManager.getCurrentUser().setfName(input);
    }

    //change the current user last name
    private void changeLName(){
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
        viewManager.getCurrentUser().setlName(input);
    }

    //get a specific address
    private void pickAddress(MyArrayList<Address> list) throws SQLException {
        String input="";
        boolean run = true;
        while (run) {
            System.out.println("Which address would you like to change?: ");
            input = scanner.next();
            boolean valid = true;
            for(int i=0; i<input.length(); i++){
                if (!Character.isDigit(input.charAt(i)))
                    valid=false;
            }
            if(!valid || input=="")
                System.out.println("Not a choice\n");
            else
                run=false;
        }
        list.get(Integer.parseInt(input)).setStreetName(streetName());
        list.get(Integer.parseInt(input)).setStreetNum(streetNum());
        addressDAO.save(list.get(Integer.parseInt(input)));
    }

    //change the current user street name
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

    //change the current user street num
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
}
