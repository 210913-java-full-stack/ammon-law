package views;

import DAO.AccountDAO;
import models.Account;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateAccount extends View{
    AccountDAO dao = new AccountDAO(viewManager.getConn());

    public CreateAccount(Scanner scanner){super("CreateAccount",scanner);}

    @Override
    public void renderView() throws SQLException {
        makeAccount();

        System.out.println("------Where next------\nEnter selection:\n1: User Menu\n2: Main Menu\nQ: Quit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                viewManager.navigate("UserMenu");
                break;
            case "2":
                viewManager.navigate("MainMenu");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }

    private void makeAccount() throws SQLException {
        float balance=0;
        boolean run=true;
        boolean build=false;
        while (run) {
            System.out.println("Enter your initial deposit: ");
            String input = scanner.nextLine();

            boolean valid = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.')
                    valid = false;
            }

            if (!valid) {
                System.out.println("Incorrect Entry, Press Y to try again");
                input = scanner.nextLine();
                if(input!="y" && input!="Y")
                    run=false;
            }
            else {
                balance = Float.parseFloat(input);
                run = false;
                build = true;
            }
        }

        Account newAccount;
        if(build) {
            newAccount = new Account(viewManager.getCurrentUser().getID(), balance);
            dao.create(newAccount);
        }
    }
}
