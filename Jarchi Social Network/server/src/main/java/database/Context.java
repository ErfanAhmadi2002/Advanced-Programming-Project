package database;


import java.io.FileNotFoundException;

public class Context {
    public UserDatabase userDataBase;
    public TweetDatabase tweetDataBase;
    public CommentDatabase commentDataBase;
    public MessageDatabase messageDataBase;
    public BotDataBase botDataBase;
    public PreviousData previousData;
    public GroupDataBase groupDataBase;
    public ImageDataBase imageDataBase;

    public Context() throws FileNotFoundException {
        this.userDataBase = new UserDatabase();
        this.tweetDataBase = new TweetDatabase();
        this.previousData = new PreviousData();
        this.commentDataBase = new CommentDatabase();
        this.messageDataBase = new MessageDatabase();
        this.groupDataBase = new GroupDataBase();
        this.imageDataBase = new ImageDataBase();
        this.botDataBase = new BotDataBase();
    }
}
