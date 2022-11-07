package apps.listeners.personalListeners.lists;

import apps.controller.personal.UserListsController;
import apps.view.pages.personal.blackList.BlockedUserPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class UnblockListener implements PageListener {
    private UserListsController userListsController;
    @Override
    public void eventOccurred(Page source) {
        userListsController = new UserListsController();
        userListsController.unblockUser((BlockedUserPane) source);
    }
}
