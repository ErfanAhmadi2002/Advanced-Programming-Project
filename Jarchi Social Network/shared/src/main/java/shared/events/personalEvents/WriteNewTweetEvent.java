package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class WriteNewTweetEvent extends Event {
    private final String text;
    private final byte[] image;

    public WriteNewTweetEvent(int authToken, String text, byte[] image) {
        super(authToken);
        this.text = text;
        this.image = image;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeNewTweet(this);
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }
}
