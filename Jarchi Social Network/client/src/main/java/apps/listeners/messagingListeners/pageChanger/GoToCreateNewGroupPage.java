package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.groupMessaging.GroupMessaging;
import listeners.PageListener;
import shared.models.Group;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;
import java.util.ArrayList;

public class GoToCreateNewGroupPage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        ArrayList<Group> allGroups = ((GroupMessaging)source).getAllGroups();
        ExtraPageChangers.goToCreateNewGroupPage(allGroups);
    }
}
