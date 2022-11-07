package apps.listeners.messagingListeners.multipleMessaging;

import apps.view.pages.messaging.multipleMessaging.CategoryPane;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;
import java.util.ArrayList;

public class SendMessageToCategoryListener implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        ArrayList<UserCopy> usersToBeSent = ((CategoryPane)source).getUsers();
        ExtraPageChangers.goToCreateMultipleMessage(usersToBeSent);
    }
}
