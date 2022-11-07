package apps.listeners.settingListeners.changePassword;

import apps.controller.setting.ChangePasswordController;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.NewPasswordFormEvent;
import view.Page;

import java.io.IOException;

public class ChangePasswordFormListener implements FormListener {
    private ChangePasswordController changePasswordController;

    @Override
    public void formRequest(Form form, Page source) {
        changePasswordController = new ChangePasswordController();
        changePasswordController.changePassword((NewPasswordFormEvent) form);
    }
}
