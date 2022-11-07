package apps.listeners.settingListeners.accountActivation;

import apps.controller.setting.AccountActivationController;
import listeners.ButtonListener;

import java.io.IOException;

public class DeactivateListener implements ButtonListener {
    private AccountActivationController accountActivationController;
    @Override
    public void buttonPressed() throws IOException {
        this.accountActivationController = new AccountActivationController();
        accountActivationController.deactivateAccount();
    }
}
