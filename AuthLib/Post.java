package AuthLib;

public class Post extends PostAbs{

    public Post(String post_text,User user){
        super(post_text,user);
    }
    @Override
    public String getPostText()
    {
        return this.post_text;
        
    }
    @Override
    public void setPostText(String text){
        this.post_text=text;
    }
    @Override
    public String getTime(){
        return this.time;
       
    }
    @Override
    public void setTime(String time){
        this.time=time;
    }
    @Override
    public User getUser(){
        return this.user;
        
    }
    @Override
    public void setUser(User user){
       this.user=user;
    }
 
    
}
