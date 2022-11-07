package apps.listeners.messagingListeners.startPages;

import apps.controller.messaging.GroupMessagingController;
import apps.view.pages.messaging.groupMessaging.GroupMessaging;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartGroupMessagingPageListener implements PageListener {
    private GroupMessagingController groupMessagingController;
    @Override
    public void eventOccurred(Page source) {
        groupMessagingController = new GroupMessagingController();
        groupMessagingController.startGroupMessagingPage((GroupMessaging) source);
    }
}
