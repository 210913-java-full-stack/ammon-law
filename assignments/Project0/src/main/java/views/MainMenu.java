package views;

import java.util.Scanner;

public class MainMenu extends View{

    public MainMenu(Scanner scanner){
        super("MainMenu",scanner);
    }

    //navigate to login or create user, or quit
    @Override
    public void renderView() {
        System.out.println("Main Menu\nEnter selection:\n\n1: Login as User\n2: Create User\nQ: Quit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                viewManager.navigate("Login");
                break;
            case "2":
                viewManager.navigate("CreateUser");
                break;
            case"Q":
            case "q":
                viewManager.setRunning(false);
                break;
        }
    }
}
