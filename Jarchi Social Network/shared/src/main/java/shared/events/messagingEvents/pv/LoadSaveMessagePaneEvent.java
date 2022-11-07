package shared.events.messagingEvents.pv;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadSaveMessagePaneEvent extends Event {
    private final int tweetId;

    public LoadSaveMessagePaneEvent(int authToken, int tweetId) {
        super(authToken);
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadSaveMessagesPane(this);
    }

    public int getTweetId() {
        return tweetId;
    }

}
