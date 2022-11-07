package apps.listeners.personalListeners.editInfo;

import apps.controller.personal.EditInfoController;
import apps.view.pages.personal.EditInfo;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class UploadProfileImageListener implements PageListener {
    private EditInfoController editInfoController;
    @Override
    public void eventOccurred(Page source) {
        editInfoController = new EditInfoController();
        editInfoController.uploadProfileImage((EditInfo) source);
    }
}
