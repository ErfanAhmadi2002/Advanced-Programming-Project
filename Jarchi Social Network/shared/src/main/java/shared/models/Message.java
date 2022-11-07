package shared.models;

public class Message {
    private int id;
    private int imageId;
    private int senderId;
    private String text;
    private int isSeen; // 1 means offline , 2 means sent, 3 means receiver got online , 4 means seen;
    private boolean isForwarded;

    public Message(int senderId, String text , boolean isForwarded , int lastId) {
        lastId++;
        this.id = lastId;
        this.senderId = senderId;
        this.text = text;
        this.isSeen = 1;
        this.isForwarded = isForwarded;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public void setForwarded(boolean forwarded) {
        isForwarded = forwarded;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(int isSeen) {
        this.isSeen = isSeen;
    }
}
