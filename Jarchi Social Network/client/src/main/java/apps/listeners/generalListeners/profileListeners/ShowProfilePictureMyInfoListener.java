package apps.listeners.generalListeners.profileListeners;

import apps.controller.personal.EditInfoController;
import apps.view.pages.personal.MyInfo;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class ShowProfilePictureMyInfoListener implements PageListener {
    private EditInfoController editInfoController;
    @Override
    public void eventOccurred(Page source) {
        editInfoController = new EditInfoController();
        editInfoController.showProfileMyPicture((MyInfo) source);
    }
}
