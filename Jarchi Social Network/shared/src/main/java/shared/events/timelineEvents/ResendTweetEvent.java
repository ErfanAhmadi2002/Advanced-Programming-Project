package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class ResendTweetEvent extends Event {
    private final int tweetId;

    public ResendTweetEvent(int authToken, int tweetId) {
        super(authToken);
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.resendTweet(this);
    }

    public int getTweetId() {
        return tweetId;
    }

}
