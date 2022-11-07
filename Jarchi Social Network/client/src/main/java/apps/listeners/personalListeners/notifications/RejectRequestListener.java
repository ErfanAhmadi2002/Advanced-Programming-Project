package apps.listeners.personalListeners.notifications;

import apps.controller.personal.NotificationsController;
import apps.view.pages.personal.notifications.NewRequestsNotificationPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class RejectRequestListener implements PageListener {
    private NotificationsController notificationsController;
    @Override
    public void eventOccurred(Page source) {
        notificationsController = new NotificationsController();
        notificationsController.rejectRequest((NewRequestsNotificationPane) source);
    }
}
