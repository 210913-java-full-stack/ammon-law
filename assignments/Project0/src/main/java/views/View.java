package views;

import utils.ViewManager;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class View {
    protected Scanner scanner;
    protected String viewName;
    protected ViewManager viewManager;

    public View(String viewName, Scanner scanner){
        this.viewName=viewName;
        this.scanner=scanner;
        this.viewManager=ViewManager.getViewManager();
    }

    public String getViewName(){
        return viewName;
    }

    public abstract void renderView() throws SQLException;
}
