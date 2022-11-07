package apps.listeners.messagingListeners.startPages;

import apps.controller.messaging.SavedMessagesController;
import apps.view.pages.messaging.savedMessages.SavedMessages;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartSavedMessagesListener implements PageListener {
    private SavedMessagesController savedMessagesController;
    @Override
    public void eventOccurred(Page source) {
        savedMessagesController = new SavedMessagesController();
        savedMessagesController.startSavedMessage((SavedMessages) source);
    }
}
