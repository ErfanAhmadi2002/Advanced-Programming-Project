package shared.models;

public class MessageCopy {
    private final int id;
    private final String text;
    private final int senderId;
    private final boolean isForwarded;
    private final int imageId;
    private final int seenState;

    public MessageCopy(Message message) {
        this.id = message.getId();
        this.text = message.getText();
        this.senderId = message.getSenderId();
        this.isForwarded = message.isForwarded();
        this.imageId = message.getImageId();
        this.seenState = message.getIsSeen();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getSenderId() {
        return senderId;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public int getImageId() {
        return imageId;
    }

    public int getSeenState() {
        return seenState;
    }
}
