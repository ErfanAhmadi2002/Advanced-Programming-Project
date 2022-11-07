package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class BlockWriterEvent extends Event {
    private final UserCopy writer;

    public BlockWriterEvent(int authToken, UserCopy writer) {
        super(authToken);
        this.writer = writer;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.blockWriter(this);
    }

    public UserCopy getWriter() {
        return writer;
    }

}
