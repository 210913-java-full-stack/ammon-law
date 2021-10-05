package utils;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
    private static Connection conn;

    private ConnectionManager(){

    }

    //this method takes the database connection info in the connection properties file and builds the database url
    // connection out of it. After that we attempt to get the connection to the database using the drivermanager to get
    // the connection to put in our static conn
    public static Connection getConnection()
    {
        if(conn == null) {
            try {
                Properties props = new Properties();
                FileReader connectionProperties = new FileReader("src/main/resources/connections.properties");
                props.load(connectionProperties);

                //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
                String connString = "jdbc:mariadb://" +
                        props.getProperty("hostname") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("databaseName") + "?user=" +
                        props.getProperty("user") + "&password=" +
                        props.getProperty("password");


                conn = DriverManager.getConnection(connString);
            }catch (SQLException | IOException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
