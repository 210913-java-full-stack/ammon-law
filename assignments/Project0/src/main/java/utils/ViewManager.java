package utils;

import MyArrayList.MyArrayList;
import models.Account;
import models.User;
import views.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewManager {
    private static ViewManager viewManager;
    private static View nextView;
    private MyArrayList<View> viewList;
    private boolean running;
    private Connection conn;
    private Scanner scanner;
    private static User currentUser;
    private Account currentAccount;

    private ViewManager(){
        conn = ConnectionManager.getConnection();
        scanner = new Scanner(System.in);
        viewList = new MyArrayList<View>();
        running=true;
        viewManager = this;

        viewList.add(new MainMenu(scanner));
        viewList.add(new Login(scanner));
        viewList.add(new CreateUser(scanner));
        viewList.add(new UserMenu(scanner));
        viewList.add(new CreateAccount(scanner));
        viewList.add(new AccountMenu(scanner));
        viewList.add(new UserSettings(scanner));
    }

    public static ViewManager getViewManager(){
        if(viewManager==null)
            viewManager=new ViewManager();
        return viewManager;
    }

    public void navigate(String destination){
        View view;
        for(int i=0; i<viewList.size(); i++){
            view = viewList.get(i);
            if(view.getViewName().equals(destination))
                nextView=view;
        }
    }


    public void goToNextView() throws SQLException{
        nextView.renderView();
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){this.running=running;}

    public Connection getConn() {
        return conn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account account){
        this.currentAccount=account;
    }
}
