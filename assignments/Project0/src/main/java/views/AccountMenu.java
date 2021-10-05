package views;

import DAO.AccountDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountMenu extends View{
    public AccountMenu(Scanner scanner){
        super("AccountMenu",scanner);
    }
    AccountDAO dao = new AccountDAO(viewManager.getConn());

    @Override
    public void renderView() throws SQLException {
        System.out.println("Account Menu\nEnter selection:\n\n1: Withdraw\n2: Deposit\n3:User Menu\nQ: Quit\n");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                withdraw();
                dao.save(viewManager.getCurrentAccount());
                break;
            case "2":
                deposit();
                dao.save(viewManager.getCurrentAccount());
                break;
            case "3":
                viewManager.navigate("UserMenu");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }

    private void withdraw() throws SQLException {
        float balance=viewManager.getCurrentAccount().getBalance();
        float withdraw;
        boolean run=true;
        while (run) {
            System.out.println("Enter your withdraw: ");
            String input = scanner.next();

            boolean valid = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.')
                    valid = false;
            }

            if (!valid) {
                System.out.println("Incorrect Entry, Press Y to try again");
                input = scanner.next();
                if(input!="y" && input!="Y")
                    run=false;
            }
            else {
                withdraw=Math.abs(Float.parseFloat(input));
                balance-=withdraw;
                viewManager.getCurrentAccount().setBalance(balance);
                dao.save(viewManager.getCurrentAccount());
                run = false;
            }
        }
    }

    private void deposit() throws SQLException {
        float balance=viewManager.getCurrentAccount().getBalance();
        float deposit;
        boolean run=true;
        while (run) {
            System.out.println("Enter your deposit: ");
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
                deposit=Math.abs(Float.parseFloat(input));
                balance+=deposit;
                viewManager.getCurrentAccount().setBalance(balance);
                dao.save(viewManager.getCurrentAccount());
                run = false;
            }
        }
    }
}
