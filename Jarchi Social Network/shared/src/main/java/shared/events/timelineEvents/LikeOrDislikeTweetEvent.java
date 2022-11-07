package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LikeOrDislikeTweetEvent extends Event {
    private final boolean liked;
    private final int tweetId;

    public LikeOrDislikeTweetEvent(int authToken, boolean liked, int tweetId) {
        super(authToken);
        this.liked = liked;
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.likeOrDislike(this);
    }

    public boolean isLiked() {
        return liked;
    }

    public int getTweetId() {
        return tweetId;
    }
}
