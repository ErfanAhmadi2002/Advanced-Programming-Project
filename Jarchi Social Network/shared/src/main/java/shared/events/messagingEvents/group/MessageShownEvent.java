package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class MessageShownEvent extends Event {
    private final int messageId;

    public MessageShownEvent(int authToken, int messageId) {
        super(authToken);
        this.messageId = messageId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.messageShown(this);
    }

    public int getMessageId() {
        return messageId;
    }
}
