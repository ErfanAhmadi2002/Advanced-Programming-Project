package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadMyTweetsEvent extends Event{

    public LoadMyTweetsEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadMyTweets(this);
    }

}
