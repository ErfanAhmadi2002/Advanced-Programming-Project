package apps.listeners.personalListeners.lists;

import apps.controller.personal.UserListsController;
import apps.view.pages.personal.followings.FollowingPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class UnfollowListener implements PageListener {
    private UserListsController userListsController;
    @Override
    public void eventOccurred(Page source) {
        userListsController = new UserListsController();
        userListsController.unfollowUser((FollowingPane) source);
    }
}
