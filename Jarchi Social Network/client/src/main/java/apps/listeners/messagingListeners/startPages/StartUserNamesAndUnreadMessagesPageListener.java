package apps.listeners.messagingListeners.startPages;

import apps.controller.messaging.MessagingController;
import apps.view.pages.messaging.messageWithKnown.UserNamesAndUnreadMessages;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartUserNamesAndUnreadMessagesPageListener implements PageListener {
    private MessagingController messagingController;
    @Override
    public void eventOccurred(Page source) {
        messagingController = new MessagingController();
        messagingController.startUserNamesAndUnreadMessagesPage((UserNamesAndUnreadMessages) source);
    }
}
