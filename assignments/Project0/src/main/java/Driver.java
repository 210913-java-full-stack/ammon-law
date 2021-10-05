import utils.ConnectionManager;


import java.sql.Connection;
import java.sql.*;
import utils.ConnectionManager;
import utils.ViewManager;

//After building our view manager we navigate to the main menu view and have a while loop that maintains our program
public class Driver {
    public static void main(String[] args){
        ViewManager viewManager = ViewManager.getViewManager();

        Connection conn = ConnectionManager.getConnection();

        viewManager.navigate("MainMenu");
        while(viewManager.isRunning()){
            try{
                viewManager.goToNextView();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
