package shared.events.explorerEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class GetMainTweetsEvent extends Event {

    public GetMainTweetsEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getMainTweets(this);
    }

}
