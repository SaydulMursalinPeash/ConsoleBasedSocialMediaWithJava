package AuthLib;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseStore {
    private final String url;
    private Connection connection;
    private Statement statement;

    public DatabaseStore(String url) {
        this.url = url;
    }
    public void connect() throws SQLException {
        try {
            //System.out.println("Database connection starts..");
            connection = DriverManager.getConnection(this.url);
            //System.out.println("++ Database connected successfully.");
            statement=connection.createStatement();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
    public Boolean createUser(User user) throws SQLException {
        String com="INSERT INTO users(user_name,email,password,phone) VALUES(?,?,?,?)";
        try{
            PreparedStatement pre=connection.prepareStatement(com);
            pre.setString(1,user.getName());
            pre.setString(2,user.getEmail());
            pre.setString(3,user.getPassword());
            pre.setString(4,user.getPhone());
            pre.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ArrayList getUsers(){
        ArrayList<User> users = new ArrayList<User>();
        String com="SELECT * FROM users";
        try{
            Statement s=connection.createStatement();
            ResultSet res=s.executeQuery(com);
            while(res.next()){
                User u=new User(res.getString("user_name"),res.getString("email"),res.getString("phone"),res.getString("password"));
                u.setId(res.getInt("id"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }





    //postCreate to database
    public void create_post(Post post){
        String command="INSERT INTO posts(post_text,time,user_id) VALUES(?,?,?)";
        try{
            PreparedStatement prep=connection.prepareStatement(command);
            prep.setString(1,post.getPostText());
            prep.setString(2,post.getTime());
            prep.setInt(3,post.getUser().getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private User getUserById(int id){
        User selected;
        selected = null;
        ArrayList<User> us=this.getUsers();
        int size=this.getUsers().size();
        for (int i=0;i<size;i++){
            if(us.get(i).getId()==id){
                selected=us.get(i);
            }
        }
        return selected;
    }

    ///get All post

    public ArrayList<Post> getAllPost() throws SQLException {
        ArrayList<Post> posts=new ArrayList<>();
        String com="SELECT * FROM posts";
        Statement state=connection.createStatement();
        ResultSet result=state.executeQuery(com);
        while (result.next()){
            Post post=new Post(result.getString("post_text"),getUserById(result.getInt("user_id")));
            posts.add(post);
        }
        return posts;

    }

    protected void deleteUser(User user) throws SQLException {
        String com="DELETE FROM users WHERE id=?";
        String com1="DELETE FROM posts WHERE user_id=?";
        try{
            PreparedStatement state=connection.prepareStatement(com1);
            state.setInt(1,user.getId());
            state.executeUpdate();
            state=connection.prepareStatement(com);
            state.setInt(1,user.getId());
            state.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}