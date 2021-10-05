package models;

public class Address {
    private int addressID;
    private int userID;
    private String streetName;
    private String streetNum;

    public Address()
    {

    }

    public Address(int uID, String sName, String sNum)
    {
        userID=uID;
        streetName=sName;
        streetNum=sNum;
    }

    public Address(int aID, int uID, String sName, String sNum)
    {
        addressID=aID;
        userID=uID;
        streetName=sName;
        streetNum=sNum;
    }

    public int getAddressID(){
        return addressID;
    }

    public void setAddressID(int id){
        addressID=id;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int id){
        userID=id;
    }

    public String getStreetName(){
        return streetName;
    }

    public void setStreetName(String sName){
        streetName=sName;
    }

    public String getStreetNum(){
        return streetNum;
    }

    public void setStreetNum(String sNum){
        streetNum=sNum;
    }
}
