package shared.events.messagingEvents.general;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class EditMessageEvent extends Event {
    private final String newText;
    private final int messageId;

    public EditMessageEvent(String newText, int authToken, int messageId) {
        super(authToken);
        this.newText = newText;
        this.messageId = messageId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.editMessage(this);
    }

    public int getMessageId() {
        return messageId;
    }

    public String getNewText() {
        return newText;
    }
}
