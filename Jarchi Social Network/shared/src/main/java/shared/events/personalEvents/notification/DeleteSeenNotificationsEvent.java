package shared.events.personalEvents.notification;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class DeleteSeenNotificationsEvent extends Event {
    private final int notificationType;

    public DeleteSeenNotificationsEvent(int authToken, int notificationType) {
        super(authToken);
        this.notificationType = notificationType;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.deleteSeenNotifications(this);
    }

    public int getNotificationType() {
        return notificationType;
    }
}
