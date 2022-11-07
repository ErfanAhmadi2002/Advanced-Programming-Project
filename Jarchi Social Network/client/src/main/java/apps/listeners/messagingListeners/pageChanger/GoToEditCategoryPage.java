package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.multipleMessaging.CategoryPane;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;
import java.util.ArrayList;

public class GoToEditCategoryPage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        ArrayList<UserCopy> members = ((CategoryPane)source).getUsers();
        String categoryName = ((CategoryPane)source).getName();
        ExtraPageChangers.goToEditCategoryPage(members , categoryName);
    }
}
