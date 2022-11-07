package apps.listeners.personalListeners.editInfo;

import apps.controller.personal.EditInfoController;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.NewNameFormEvent;
import view.Page;

import java.io.IOException;

public class SubmitNewFirstNameListener implements FormListener {
    private EditInfoController editInfoController;
    @Override
    public void formRequest(Form form, Page source) {
        editInfoController = new EditInfoController();
        editInfoController.changeFirstName((NewNameFormEvent) form);
    }
}
