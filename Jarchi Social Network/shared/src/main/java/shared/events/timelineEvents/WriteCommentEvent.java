package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.NewCommentFormEvent;
import shared.responses.Response;

public class WriteCommentEvent extends Event {
    private final NewCommentFormEvent newCommentFormEvent;
    private final int tweetId;


    public WriteCommentEvent(int authToken, NewCommentFormEvent newCommentFormEvent, int tweetId) {
        super(authToken);
        this.newCommentFormEvent = newCommentFormEvent;
        this.tweetId = tweetId;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeComment(this);
    }

    public NewCommentFormEvent getNewCommentFormEvent() {
        return newCommentFormEvent;
    }

    public int getTweetId() {
        return tweetId;
    }
}
