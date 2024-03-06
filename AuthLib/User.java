package AuthLib;

public class User implements UserFunctions {
    private String user_name,email,password;
    private String phone;
    private int userId;

    public User( String user_name, String email, String phone,String password) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.phone=phone;
    }


    @Override
    public void printInfo() {
        System.out.print("name: "+this.user_name+"\n"+"Email: "+this.email+"\npassword: "+this.password+"\n");

    }

    @Override
    public String getName() {
        return this.user_name;
    }

    @Override
    public void setName(String name) {
        this.user_name=name;

    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public void setPhone(String phone) {
        this.phone=phone;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    public void setId(int id){
        this.userId=id;
    }
    public int getId(){
        return this.userId;
    }
}