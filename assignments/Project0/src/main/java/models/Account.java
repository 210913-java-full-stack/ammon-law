package models;

public class Account {
    private int accountID;
    private int userID;
    private float balance;

    public Account(){}

    public Account(int uID, float bal)
    {
        userID=uID;
        balance=bal;
    }

    public Account(int aID, int uID, float bal)
    {
        accountID=aID;
        userID=uID;
        balance=bal;
    }

    public int getAccountID(){return accountID;}

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getUserID(){return userID;}

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getBalance(){return balance;}

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
