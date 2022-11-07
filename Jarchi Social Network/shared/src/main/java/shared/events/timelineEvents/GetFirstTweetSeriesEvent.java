package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class GetFirstTweetSeriesEvent extends Event {

    public GetFirstTweetSeriesEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getFirstTweetSeries(this);
    }

}
