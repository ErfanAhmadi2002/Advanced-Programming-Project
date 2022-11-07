package apps.listeners.messagingListeners.multipleMessaging;

import apps.controller.messaging.MultipleMessagingController;
import apps.view.pages.messaging.multipleMessaging.CreateCategoryPage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class CreateNewCategoryNameListener implements PageListener {
    private MultipleMessagingController multipleMessagingController;
    @Override
    public void eventOccurred(Page source) {
        multipleMessagingController = new MultipleMessagingController();
        multipleMessagingController.createNewCategoryName((CreateCategoryPage) source);
    }
}
