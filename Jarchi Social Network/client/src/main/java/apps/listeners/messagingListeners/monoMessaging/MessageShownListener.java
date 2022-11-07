package apps.listeners.messagingListeners.monoMessaging;

import apps.controller.messaging.MessagingController;
import apps.view.pages.messaging.generalMessagePages.MessagePane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class MessageShownListener implements PageListener {
    private MessagingController messagingController;
    @Override
    public void eventOccurred(Page source) {
        messagingController = new MessagingController();
        messagingController.messageShown((MessagePane) source);
    }
}
