package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.groupMessaging.GroupHistory;
import listeners.PageListener;
import shared.models.Group;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToCreateNewGroupMessagePage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        Group group = ((GroupHistory)source).getGroup();
        ExtraPageChangers.goToCreateNewGroupMessagePage(group);
    }
}
