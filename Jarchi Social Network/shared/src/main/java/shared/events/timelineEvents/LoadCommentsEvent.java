package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadCommentsEvent extends Event {
    private final int tweetId;

    public LoadCommentsEvent(int authToken, int tweetId) {
        super(authToken);
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadComments(this);
    }

    public int getTweetIds() {
        return tweetId;
    }
}
