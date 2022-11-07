package shared.events.messagingEvents.general;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class DeleteMessageEvent extends Event {
    private final int messageId;

    public DeleteMessageEvent(int authToken, int messageId) {
        super(authToken);
        this.messageId = messageId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.deleteMessage(this);
    }

    public int getMessageId() {
        return messageId;
    }
}
