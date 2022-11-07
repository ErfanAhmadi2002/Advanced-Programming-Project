package apps.listeners.personalListeners.lists;

import apps.controller.personal.UserListsController;
import apps.view.pages.personal.followers.Followers;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartFollowersPage implements PageListener {
    private UserListsController userListsController;
    @Override
    public void eventOccurred(Page source) {
        userListsController = new UserListsController();
        userListsController.startFollowerPage((Followers) source);
    }
}
