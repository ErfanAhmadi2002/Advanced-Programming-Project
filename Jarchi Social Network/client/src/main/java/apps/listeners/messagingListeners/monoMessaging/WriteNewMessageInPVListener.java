package apps.listeners.messagingListeners.monoMessaging;

import apps.controller.messaging.MessagingController;
import apps.view.pages.messaging.generalMessagePages.CreateNewMessage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class WriteNewMessageInPVListener implements PageListener {
    private MessagingController messagingController;
    @Override
    public void eventOccurred(Page source) {
        messagingController = new MessagingController();
        messagingController.writeNewMessageInPv((CreateNewMessage) source);
    }
}
