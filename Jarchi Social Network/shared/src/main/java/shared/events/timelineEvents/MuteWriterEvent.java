package shared.events.timelineEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class MuteWriterEvent extends Event {
    private final UserCopy writer;

    public MuteWriterEvent(int authToken, UserCopy writer) {
        super(authToken);
        this.writer = writer;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.muteWriter(this);
    }

    public UserCopy getWriter() {
        return writer;
    }

}
