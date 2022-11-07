package apps.listeners.personalListeners.lists;

import apps.controller.personal.UserListsController;
import apps.view.pages.personal.followings.Followings;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartFollowingsPage implements PageListener {
    private UserListsController userListsController;
    @Override
    public void eventOccurred(Page source) {
        userListsController = new UserListsController();
        userListsController.startFollowingsPage((Followings) source);
    }
}
