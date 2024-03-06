package AuthLib;

import java.sql.SQLException;
import java.util.ArrayList;

public class Authentication {
    private ArrayList<User> users;
    private boolean loggedIn=false;
    private String current_user_name,current_user_email;
    private int userId;
    private User currentUser;
    private DatabaseStore dataStore;

    public Authentication(DatabaseStore d) {
        this.dataStore=d;
        this.users=dataStore.getUsers();
    }




    public boolean logIn(String email, String password){
        this.users=dataStore.getUsers();
        int size=this.users.size();
        //System.out.println(size);
        for (int i=0;i<size;i++){
            if(this.users.get(i).getEmail().equals(email)&&this.users.get(i).getPassword().equals(password)){
                //System.out.println("Login Successfull...\nUser data:\n");
                //currentUser=this.users.get(i);
                //this.users.get(i).printInfo();
                current_user_name=this.users.get(i).getName().toString();
                current_user_email=this.users.get(i).getEmail().toString();
                System.out.println("Success---------------------");
                this.loggedIn=true;
                currentUser=this.users.get(i);
                userId=this.users.get(i).getId();
                return true;
            }
        }


        return false;
    }
    public User getCurrentUser(){
        return this.currentUser;

    }
    public boolean isLoggedIn(){
        return loggedIn;
    }
    public void printCurrentUserInfo(){
        System.out.println("Current user info:");
        this.users.get(userId).printInfo();
    }

    public void LogOut(){
        loggedIn=false;
        currentUser=null;
    }
    public void deleteAccount() throws SQLException {
        dataStore.deleteUser(this.getCurrentUser());
        this.LogOut();
    }


}