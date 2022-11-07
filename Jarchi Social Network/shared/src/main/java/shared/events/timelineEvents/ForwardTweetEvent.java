package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class ForwardTweetEvent extends Event {
    private final int tweetId;
    private final String selectedName;

    public ForwardTweetEvent(int authToken, int tweetId, String selectedName) {
        super(authToken);
        this.tweetId = tweetId;
        this.selectedName = selectedName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.forwardTweet(this);
    }

    public String getSelectedName() {
        return selectedName;
    }

    public int getTweetId() {
        return tweetId;
    }

}
