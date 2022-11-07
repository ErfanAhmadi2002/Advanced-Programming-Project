package shared.events.messagingEvents.pv;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class WriteMessageInPVEvent extends Event {
    private final String messageText;
    private final UserCopy sender;
    private final UserCopy receiver;
    private final byte[] image;

    public WriteMessageInPVEvent(int authToken, String messageText, UserCopy sender, UserCopy receiver, byte[] image) {
        super(authToken);
        this.messageText = messageText;
        this.sender = sender;
        this.receiver = receiver;
        this.image = image;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeMessageInPV(this);
    }

    public UserCopy getReceiver() {
        return receiver;
    }

    public UserCopy getSender() {
        return sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public byte[] getImage() {
        return image;
    }
}
