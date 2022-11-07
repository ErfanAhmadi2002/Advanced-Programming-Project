package apps.listeners.settingListeners.deleteAccount;

import apps.controller.setting.DeleteAccountController;
import listeners.ButtonListener;

import java.io.IOException;

public class DeleteAccountListener implements ButtonListener {
    private DeleteAccountController deleteAccountController;

    @Override
    public void buttonPressed() throws IOException {
        deleteAccountController.deleteAccount();
    }
}
