package DAO;

import DAO.UserCrud;
import MyArrayList.MyArrayList;
import models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


public class AddressDAO implements UserCrud<Address> {
    private Connection conn;

    public AddressDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void create(Address address) throws SQLException {
        String sql = "insert into address (userID, streetName, streetNum) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        pstmt.setInt(1, address.getUserID());
        pstmt.setString(2, address.getStreetName());
        pstmt.setString(3, address.getStreetNum());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();

        rs.next();
        address.setAddressID(rs.getInt("addressID"));
    }

    @Override
    public void save(Address a) throws SQLException{
        String sql = "SELECT * FROM address WHERE addressID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, a.getAddressID());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            String updateStmt = "UPDATE address SET userID = ?, streetName = ?, streetNum = ? WHERE addressID = ?";
            PreparedStatement prepUpdateStmt = conn.prepareStatement(updateStmt);
            prepUpdateStmt.setInt(1,a.getUserID());
            prepUpdateStmt.setString(2,a.getStreetName());
            prepUpdateStmt.setString(3,a.getStreetNum());
            prepUpdateStmt.setInt(4,a.getAddressID());

            prepUpdateStmt.executeUpdate();
        }
        else{
            String saveStmt = "insert into address (userID, streetName, streetNum) VALUES (?, ?, ?)";
            PreparedStatement prepSaveStmt = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            pstmt.setInt(1, a.getUserID());
            pstmt.setString(2, a.getStreetName());
            pstmt.setString(3, a.getStreetNum());

            prepSaveStmt.executeUpdate();

            rs = prepSaveStmt.getGeneratedKeys();

            rs.next();
            a.setAddressID(rs.getInt("addressID"));
        }
    }


    //to do
    @Override
    public Address getByID(int id){
        return null;
    }

    @Override
    public MyArrayList<Address> getAll(){
        return null;
    }

    @Override
    public void deleteByID(int id){

    }

    public MyArrayList<Address> getByUserID(int id) throws SQLException{
        String sql = "SELECT * FROM address WHERE userID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        MyArrayList<Address> resultList = new MyArrayList<Address>();

        while(rs.next()) {
            Address newAddress = new Address(rs.getInt("addressID"), rs.getInt("userID"), rs.getString("streetName"), rs.getString("streetNum"));
            resultList.add(newAddress);
        }

        return resultList;
    }
}
