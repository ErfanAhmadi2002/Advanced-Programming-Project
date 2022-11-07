package apps.listeners.personalListeners.lists;

import apps.controller.personal.UserListsController;
import apps.view.pages.personal.blackList.BlackList;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartBlackListPage implements PageListener {
    private UserListsController userListsController;
    @Override
    public void eventOccurred(Page source) {
        userListsController = new UserListsController();
        userListsController.startBlackListPage((BlackList) source);
    }
}
