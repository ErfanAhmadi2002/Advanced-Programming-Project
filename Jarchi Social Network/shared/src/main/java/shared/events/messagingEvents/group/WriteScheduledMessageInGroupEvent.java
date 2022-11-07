package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class WriteScheduledMessageInGroupEvent extends Event {
    private final String messageText;
    private final String second;
    private final String minute;
    private final String  hour;
    private final int groupId;
    private final byte[] image;

    public WriteScheduledMessageInGroupEvent(int authToken, String messageText, String second, String minute, String hour, int groupId, byte[] image) {
        super(authToken);
        this.messageText = messageText;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.groupId = groupId;
        this.image = image;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.writeScheduledMessageInGroup(this);
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSecond() {
        return second;
    }

    public String getMinute() {
        return minute;
    }

    public String getHour() {
        return hour;
    }

    public byte[] getImage() {
        return image;
    }

    public int getGroupId() {
        return groupId;
    }
}
