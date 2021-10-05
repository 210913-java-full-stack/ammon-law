package DAO;

import MyArrayList.MyArrayList;
import models.Account;
import models.User;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AccountDAO implements UserCrud<Account>{
    private Connection conn;


    public AccountDAO(Connection conn){
        this.conn = conn;
    }

    //given the user data other than the accountID, insert the data into the account table. Afterwards show the account
    //id to the user to be used later
    @Override
    public void create(Account account) throws SQLException {
        String sql = "insert into accounts (userID, balance) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        pstmt.setInt(1, account.getUserID());
        pstmt.setFloat(2, account.getBalance());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();

        rs.next();
        account.setAccountID(rs.getInt("accountID"));
    }

    //save the data a user has updated as well as make an account if the user hasn't been made one
    @Override
    public void save(Account a) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE accountID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, a.getAccountID());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            String updateStmt = "UPDATE accounts SET userID = ?, balance = ? WHERE accountID = ?";
            PreparedStatement prepUpdateStmt = conn.prepareStatement(updateStmt);
            prepUpdateStmt.setInt(1,a.getUserID());
            prepUpdateStmt.setFloat(2,a.getBalance());
            prepUpdateStmt.setInt(3, a.getAccountID());

            prepUpdateStmt.executeUpdate();
        }
        else{
            String saveStmt = "INSERT into accounts (userID, balance) VALUES (?, ?)";
            PreparedStatement prepSaveStmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            pstmt.setInt(1, a.getUserID());
            pstmt.setFloat(2, a.getBalance());

            prepSaveStmt.executeUpdate();

            rs = prepSaveStmt.getGeneratedKeys();

            rs.next();
            a.setAccountID(rs.getInt("addressID"));
        }
    }


    //didn't use
    @Override
    public Account getByID(int id){
        return null;
    }

    //get account list that have the user id given
    public MyArrayList<Account> getByUserID(int id) throws SQLException{
        String sql = "SELECT * FROM accounts WHERE userID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        MyArrayList<Account> resultList = new MyArrayList<Account>();

        while(rs.next()) {
            Account newAccount = new Account(rs.getInt("accountID"), rs.getInt("userID"), rs.getFloat("balance"));
            resultList.add(newAccount);
        }

        return resultList;
    }

    //didn't use
    @Override
    public MyArrayList<Account> getAll(){
        return null;
    }

    //didn't use
    @Override
    public void deleteByID(int id){

    }
}
