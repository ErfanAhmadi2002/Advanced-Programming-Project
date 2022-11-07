package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.generalMessagePages.MessageHistory;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;
import java.util.ArrayList;

public class GoToCreateNewMessagePage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        ArrayList<UserCopy> usersInChat = ((MessageHistory)source).getUsersInChat();
        ExtraPageChangers.goToCreateNewMessagePage(usersInChat);
    }
}
