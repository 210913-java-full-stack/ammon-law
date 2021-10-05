package views;

import DAO.AccountDAO;
import MyArrayList.MyArrayList;
import models.Account;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu extends View{
    AccountDAO dao = new AccountDAO(viewManager.getConn());

    public UserMenu(Scanner scanner){
        super("UserMenu",scanner);
    }

    //Show the accounts the user has, then have them decide if they want to do anything with those accounts or change
    //the user data
    @Override
    public void renderView() throws SQLException {

        MyArrayList<Account> userAccounts = dao.getByUserID(viewManager.getCurrentUser().getID());

        System.out.println("------Account List------\n");
        for(int i=0; i<userAccounts.size(); i++){
            System.out.println("Account " + i+1 + ": Account Number " + userAccounts.get(i).getAccountID() + ", Balance " + userAccounts.get(i).getBalance());
        }

        System.out.println("User Menu\nEnter selection:\n\n1: Enter Account\n2: Create Account\n3: User Settings\n4: Main Menu\nQ: Quit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                boolean next = pickAccount(userAccounts);
                if(next)
                    viewManager.navigate("AccountMenu");
                break;
            case "2":
                viewManager.navigate("CreateAccount");
                break;
            case "3":
                viewManager.navigate("UserSettings");
                break;
            case "4":
                viewManager.navigate("MainMenu");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }

    //used to pick a valid account and move to it's menu
    private boolean pickAccount(MyArrayList<Account> userAccounts){
        boolean run=true;
        boolean next=false;
        while (run) {
            boolean valid=true;
            if(userAccounts.size()>0) {
                System.out.println("Enter your account ID: ");
                String input = scanner.next();
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isDigit(input.charAt(i)))
                        valid = false;
                }
                int id = Integer.parseInt(input);
                if (!valid || userAccounts.size() < id || id < 1 || input == "") {
                    System.out.println("Invalid Account ID\n");
                    System.out.println("Press Y to Try Again: ");
                    input = scanner.next();
                    if(input!="y" && input!="Y")
                        System.out.println(input);
                        run=false;
                }
                else {
                    run = false;
                    next = true;
                    viewManager.setCurrentAccount(userAccounts.get(id-1));
                    System.out.println(userAccounts.get(id-1).getUserID());
                }
            }
            else
            {
                run = false;
                System.out.println("No Accounts");
            }
        }
        return next;
    }
}
