package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class WriteNewMessageInGroupEvent extends Event {
    private final String messageText;
    private final byte[] image;
    private final int groupId;

    public WriteNewMessageInGroupEvent(String messageText, int authToken, byte[] image, int groupId) {
        super(authToken);
        this.messageText = messageText;
        this.image = image;
        this.groupId = groupId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeNewMessageInGroup(this);
    }

    public int getGroupId() {
        return groupId;
    }

    public String getMessageText() {
        return messageText;
    }

    public byte[] getImage() {
        return image;
    }
}
