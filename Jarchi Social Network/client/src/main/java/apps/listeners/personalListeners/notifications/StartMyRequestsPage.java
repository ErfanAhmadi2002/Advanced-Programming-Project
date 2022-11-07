package apps.listeners.personalListeners.notifications;

import apps.controller.personal.NotificationsController;
import apps.view.pages.personal.notifications.MyRequestsState;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartMyRequestsPage implements PageListener {
    private NotificationsController notificationsController;
    @Override
    public void eventOccurred(Page source) {
        notificationsController = new NotificationsController();
        notificationsController.startMyRequestsPage((MyRequestsState) source);
    }
}
