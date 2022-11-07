package apps.listeners.generalListeners.profileListeners;

import apps.controller.general.ShowProfileController;
import apps.view.pages.generalPages.ProfilePage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class MuteButtonListener implements PageListener {
    private ShowProfileController showProfileController;
    @Override
    public void eventOccurred(Page source) {
        showProfileController = new ShowProfileController();
        showProfileController.muteUserRequest((ProfilePage) source);
    }
}
