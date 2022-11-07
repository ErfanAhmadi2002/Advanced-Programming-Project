package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class SaveTweetEvent extends Event {
    private final int tweetId;

    public SaveTweetEvent(int authToken, int tweetId) {
        super(authToken);
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.saveTweet(this);
    }

    public int getTweetId() {
        return tweetId;
    }

}
