package apps.listeners.messagingListeners.multipleMessaging;

import apps.controller.messaging.MultipleMessagingController;
import apps.view.pages.messaging.multipleMessaging.CategoryPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class RemoveCategoryListener implements PageListener {
    private MultipleMessagingController multipleMessagingController;
    @Override
    public void eventOccurred(Page source) {
        multipleMessagingController = new MultipleMessagingController();
        multipleMessagingController.removeCategory((CategoryPane) source);
    }
}
