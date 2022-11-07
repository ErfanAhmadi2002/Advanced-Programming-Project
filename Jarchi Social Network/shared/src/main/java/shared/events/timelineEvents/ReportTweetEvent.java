package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class ReportTweetEvent extends Event {
    private final int tweetId;

    public ReportTweetEvent(int authToken, int tweetId) {
        super(authToken);
        this.tweetId = tweetId;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.reportTweet(this);
    }

    public int getTweetId() {
        return tweetId;
    }

}
