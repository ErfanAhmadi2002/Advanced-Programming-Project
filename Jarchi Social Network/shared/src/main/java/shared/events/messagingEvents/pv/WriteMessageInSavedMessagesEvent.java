package shared.events.messagingEvents.pv;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class WriteMessageInSavedMessagesEvent extends Event {
    private final String text;

    public WriteMessageInSavedMessagesEvent(int authToken, String text) {
        super(authToken);
        this.text = text;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeMessageInSavedMessages(this);
    }

    public String getText() {
        return text;
    }
}
