package shared.models;

public class TweetCopy {
    private final String text;
    private final int id;
    private final UserCopy writer;
    private final MyImage image;

    public TweetCopy(Tweet tweet , UserCopy writer , MyImage image) {
        this.text = tweet.getText();
        this.id = tweet.getId();
        this.writer = writer;
        this.image = image;
    }

    public MyImage getImage() {
        return image;
    }

    public UserCopy getWriter() {
        return writer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
