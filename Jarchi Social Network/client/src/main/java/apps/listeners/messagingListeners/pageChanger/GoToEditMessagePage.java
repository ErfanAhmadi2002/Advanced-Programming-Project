package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.generalMessagePages.MessagePane;
import listeners.PageListener;
import shared.models.Message;
import shared.models.MessageCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToEditMessagePage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        MessageCopy message = ((MessagePane) source).getMessage();
        ExtraPageChangers.goToEditMessagePage(message);
    }
}
