package apps.listeners.messagingListeners.multipleMessaging;

import apps.controller.messaging.MultipleMessagingController;
import apps.view.pages.messaging.multipleMessaging.MultipleMessaging;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartMultipleMessagingListener implements PageListener {
    private MultipleMessagingController multipleMessagingController;
    @Override
    public void eventOccurred(Page source) {
        multipleMessagingController = new MultipleMessagingController();
        multipleMessagingController.startMultipleMessaging((MultipleMessaging) source);
    }
}
