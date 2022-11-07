package apps.listeners.personalListeners.notifications;

import apps.controller.personal.NotificationsController;
import listeners.ButtonListener;

import java.io.IOException;

public class DeleteSeenNotificationFollowingStateListener implements ButtonListener {
    private NotificationsController notificationsController;
    @Override
    public void buttonPressed() throws IOException {
        notificationsController = new NotificationsController();
        notificationsController.deleteSeenNotificationFollowingState();
    }
}
