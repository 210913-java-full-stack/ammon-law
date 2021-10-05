package models;

public class User {
    private int id;
    private String fName;
    private String lName;
    private String password;
    private String email;

    public User()
    {

    }

    public User(int id, String fName, String lName, String password, String email)
    {
        this.id=id;
        this.fName=fName;
        this.lName=lName;
        this.password=password;
        this.email=email;
    }

    public User(String fName, String lName, String password, String email)
    {
        this.fName=fName;
        this.lName=lName;
        this.password=password;
        this.email=email;
    }

    public int getID(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getfName(){
        return fName;
    }

    public void setfName(String fName){
        this.fName=fName;
    }

    public String getlName(){
        return lName;
    }

    public void setlName(String lName){
        this.lName=lName;
    }

    public String getPassword(){return password;}

    public void setPassword(String password){
        this.password=password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
