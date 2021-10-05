package DAO;

import MyArrayList.MyArrayList;
import models.User;
import utils.ConnectionManager;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


public class UserDAO implements UserCrud<User> {
    private Connection conn;

    //private static UserDAO userDAO;

    public UserDAO(Connection conn){
        //userDAO = this;
        this.conn = conn;
    }

    @Override
    public void create(User user) throws SQLException {
        String sql = "insert into users (fName, lName, password, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getfName());
        pstmt.setString(2, user.getlName());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getEmail());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();

        rs.next();
        user.setId(rs.getInt("userID"));

        System.out.println("Your user ID is "+ user.getID()+".");
    }

    public int createGetID(User user) throws SQLException {
        String sql = "insert into users (fName, lName, password) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getfName());
        pstmt.setString(2, user.getlName());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getEmail());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();

        rs.next();
        user.setId(rs.getInt("userID"));

        System.out.println("Your user ID is "+ user.getID()+".");
        return user.getID();
    }

    @Override
    public void save(User u) throws SQLException{
        String sql = "SELECT * FROM users WHERE userID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, u.getID());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            String updateStmt = "UPDATE users SET fName = ?, lName = ? WHERE userID = ?";
            PreparedStatement prepUpdateStmt = conn.prepareStatement(updateStmt);
            prepUpdateStmt.setString(1,u.getfName());
            prepUpdateStmt.setString(2,u.getlName());
            prepUpdateStmt.setInt(3,u.getID());

            prepUpdateStmt.executeUpdate();
        }
        //not sure this will ever be needed;
        else{
            String saveStmt ="INSERT INTO table_name (fName, lName, password, email) VALUES (?,?,?,?)";
            PreparedStatement prepSaveStmt = conn.prepareStatement(saveStmt, RETURN_GENERATED_KEYS);
            prepSaveStmt.setString(1,u.getfName());
            prepSaveStmt.setString(2,u.getlName());
            prepSaveStmt.setString(3,u.getPassword());
            prepSaveStmt.setString(4,u.getEmail());

            prepSaveStmt.executeUpdate();

            rs = prepSaveStmt.getGeneratedKeys();

            rs.next();
            u.setId(rs.getInt("userID"));
        }
    }

    //to do
    @Override
    public User getByID(int id){

        return null;
    }

    @Override
    public MyArrayList<User> getAll() throws SQLException
    {
        String sql = "SELECT * FROM users";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        MyArrayList<User> resultList = new MyArrayList<User>();

        while(rs.next()) {
            User newUser = new User(rs.getInt("userID"), rs.getString("fName"), rs.getString("lName"), rs.getString("password"), rs.getString("email"));
            resultList.add(newUser);
        }

        return resultList;
    }

    @Override
    public void deleteByID(int id){

    }

    //testing stuff
    /*public UserDAO getInstance(Connection conn){
        if (userDAO==null){
            userDAO=new UserDAO(conn);
        }
        return userDAO;
    }*/
}
