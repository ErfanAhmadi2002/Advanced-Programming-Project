package apps.listeners.messagingListeners.monoMessaging;

import apps.controller.messaging.MessagingController;
import apps.view.pages.messaging.messageWithUnknown.UserNameCheck;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class SearchUserMessageListener implements PageListener {
    private MessagingController messagingController;
    @Override
    public void eventOccurred(Page source) {
        messagingController = new MessagingController();
        messagingController.searchForMessaging((UserNameCheck) source);
    }
}
