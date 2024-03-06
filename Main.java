
import AuthLib.Authentication;
import AuthLib.User;
import AuthLib.Post;
import java.util.ArrayList;
import java.util.Scanner;
import AuthLib.DatabaseStore;
import java.sql.SQLException;

public class Main {
    private static DatabaseStore data;
    private static Authentication authentication;
    private static boolean running=true;
    public static void main(String[] args) throws SQLException{
        System.out.println("*************************** Welcome ***************************\n");
        //Connect to database....
        data=new DatabaseStore("jdbc:sqlite:H:\\ConnectAndShare\\users_data.sqlite");
        data.connect();
        authentication=new Authentication(data);

        while (running){
            mainMenu();
        }




    }
    //Menu functions

    private static void SignUpOption() throws SQLException {
        Scanner string_input=new Scanner(System.in);
        System.out.println("\n-----------------Register----------------\n");
        String name,email,password,phone;
        System.out.print("User name: ");
        name=string_input.nextLine();
        System.out.print("\nEmail: ");
        email=string_input.nextLine();
        System.out.print("\nPhone: ");
        phone=string_input.nextLine();
        System.out.print("\nPassword: ");
        password=string_input.nextLine();
        User newUser=new User(name,email,phone,password);
        boolean res=data.createUser(newUser);
        //string_input.close();
        if(res){
            LogInOption();
        }else{
            menuAfterWrongRegisterInput();

        }


    }

    private static void menuAfterWrongRegisterInput() throws SQLException {
        System.out.println("\n!!! Something went wrong...\nWhat do you want now?\n0. exit.\n1. Try again.\n2. Go to Main menu.\nChoose: ");

        Scanner input=new Scanner(System.in);
        int option=input.nextInt();
        if(option==0){
            exit();

        }
        else if(option==1){
            SignUpOption();
        }
        else if(option==2){
            mainMenu();
        }
        else{
            System.out.println("\n!!! Wrong input. Please input carefully..\n");
            menuAfterWrongRegisterInput();
        }
    }

    private static void LogInOption() throws SQLException {
        System.out.println("\n-------------------Log In--------------------\n");
        Scanner input=new Scanner(System.in);
        String email,password;
        System.out.print("Email: ");
        email=input.nextLine();
        System.out.print("Password: ");
        password=input.nextLine();
        boolean log=authentication.logIn(email,password);
        if(log==true){
            System.out.println("++Successfully logged in as "+authentication.getCurrentUser().getName()+".\n");
            showPost();

        }else{
            System.out.println("Wrong username or password.");
            menuAfterWrongLogInInput();
        }
        input.close();


    }

    private static void menuAfterWrongLogInInput() throws SQLException {
        System.out.println("!!! Something went wrong...\nWhat do you want now?\n0. exit.\n1. Try again.\n2. Go to Main menu.\nChoose: ");

        Scanner input=new Scanner(System.in);
        int option=input.nextInt();
        if(option==0){
            exit();

        }
        else if(option==1){
            LogInOption();
        }
        else if(option==2){
            mainMenu();
        }
        else{
            System.out.println("Wrong input. Please input carefully..");
            menuAfterWrongLogInInput();
        }
    }

    public static void exit(){
        System.out.println("Thank you. See you again..");
        running=false;
    }

    //main menu
    public static void mainMenu() throws SQLException {
        // 1. Create account
        // 2. Log in
        // 0. exit
        System.out.print("---------------------------Main menu---------------------------" +
                "\nOptions: \n\t1. Create account.\n\t2. Log in.\n\t0. exit.\nChoose:");
        Scanner input=new Scanner(System.in);
        int choose=input.nextInt();
        if(choose==0){
            exit();
        }
        else if(choose==1){
            SignUpOption();
        }
        else if(choose==2){
            LogInOption();
        }
        else{
            System.out.println("!!! Wrong input. Please input carefully..");
            mainMenu();
        }
        //input.close();

    }
    public static void profileData(){
        System.out.print("\n-------------------------Information of your account---------------------------\n\tName: "
                +authentication.getCurrentUser().getName()
                +"\n\tEmail: "+authentication.getCurrentUser().getEmail()
                +"\n\tPhone: "+authentication.getCurrentUser().getPhone()+"\n");
    }
    //show posts
    public static void showPost() throws SQLException {
        System.out.println("\n-------------------------Updates----------------------\n");
        ArrayList<Post> posts=data.getAllPost();
        int size=posts.size();
        for(int i=0;i<size;i++){
            Post post=posts.get(i);
            System.out.println("-------------------------------------------------------------------");
            System.out.print("|\t"+post.getUser().getName()+"\t|\t"+post.getTime()+"|\n-----------------------------------------------------------------------" +
                    "\n"+post.getPostText()+"\n");
            System.out.println("------------------------------------------------------------------");
        }
        menuAfterLogIn();
    }

    //This menu shows after login succefull.
    public static void menuAfterLogIn() throws SQLException {
        System.out.println("\n----------------------------------Menu-------------------------------\n");
        System.out.print("\nWhat do you want now:\n\t1. Write post.\n\t2. Profile data.\n\t3. Log out.\n\t4. Delete account.\n\t0. Exit.\nChoose: ");

        Scanner int_scan=new Scanner(System.in);
        int choose=int_scan.nextInt();
        if(choose==0){
            exit();
        }
        else if(choose==2){
            profileData();
            menuAfterLogIn();
        }
        else if(choose==3){
            authentication.LogOut();
            mainMenu();
        }
        else if(choose ==4){
            authentication.deleteAccount();
            mainMenu();
        }
        else if(choose==1){
            writePost();
        }
        else{
            System.out.println("Wrong input. Please input carefully..");
            menuAfterLogIn();
        }
    }

    private static void writePost() throws SQLException {
        System.out.print("Whats on your mind: ");
        Scanner text_scan=new Scanner(System.in);
        String text=text_scan.nextLine();
        Post newPost=new Post(text,authentication.getCurrentUser());
        data.create_post(newPost);
        showPost();
    }
}












