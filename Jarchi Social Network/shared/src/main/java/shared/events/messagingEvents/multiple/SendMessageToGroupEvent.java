package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class SendMessageToGroupEvent extends Event {
    private final UserCopy sender;
    private final ArrayList<UserCopy> receivers;
    private final String text;
    private final byte[] image;

    public SendMessageToGroupEvent(int authToken, UserCopy sender, ArrayList<UserCopy> receivers, String text, byte[] image) {
        super(authToken);
        this.sender = sender;
        this.receivers = receivers;
        this.text = text;
        this.image = image;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.sendMessageToGroup(this);
    }

    public UserCopy getSender() {
        return sender;
    }

    public ArrayList<UserCopy> getReceivers() {
        return receivers;
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }
}
