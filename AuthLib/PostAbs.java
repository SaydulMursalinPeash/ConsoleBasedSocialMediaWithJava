package AuthLib;
import java.time.LocalDateTime;

abstract class PostAbs {
    protected String post_text,time;
    protected User user;

    protected PostAbs(String post_text,User user){
        this.post_text=post_text;
        this.user=user;
        this.time=LocalDateTime.now().toString();
    }
    protected abstract String getPostText();
    protected abstract void setPostText(String text);
    protected abstract String getTime();
    protected abstract void setTime(String time);
    protected abstract User getUser();
    protected abstract void setUser(User user);

    
}
