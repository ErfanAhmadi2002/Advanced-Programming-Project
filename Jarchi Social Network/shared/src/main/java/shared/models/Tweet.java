package shared.models;


import java.util.ArrayList;

public class Tweet {
    private int lastId;
    private int imageId;
    private int id;
    private String text;
    private int userId;
    private ArrayList<Integer> commentsId;
    private int numberOfLikes;
    private ArrayList<Integer> usersWhoLiked;
    private ArrayList<Integer> usersWhoDisliked;
    private boolean isPrimaryTweet;

    public Tweet(String text , int userId , int lastId , boolean IsPrimaryTweet) {
        this.isPrimaryTweet = IsPrimaryTweet;
        this.userId = userId;
        this.lastId = lastId;
        lastId++;
        this.id = lastId;
        this.text = text;
        this.commentsId = new ArrayList<>();
        this.usersWhoLiked = new ArrayList<>();
        this.numberOfLikes = 0;
    }

    public boolean isPrimaryTweet() {
        return isPrimaryTweet;
    }

    public void setPrimaryTweet(boolean primaryTweet) {
        isPrimaryTweet = primaryTweet;
    }

    public ArrayList<Integer> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(ArrayList<Integer> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public ArrayList<Integer> getUsersWhoDisliked() {
        return usersWhoDisliked;
    }

    public void setUsersWhoDisliked(ArrayList<Integer> usersWhoDisliked) {
        this.usersWhoDisliked = usersWhoDisliked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(ArrayList<Integer> commentsId) {
        this.commentsId = commentsId;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
