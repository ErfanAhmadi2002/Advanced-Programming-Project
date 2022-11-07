package apps.listeners.settingListeners.generalPrivacy;

import apps.controller.setting.GeneralPrivacyController;
import listeners.ButtonListener;

import java.io.IOException;

public class MakeAccountPrivateListener implements ButtonListener {
    private GeneralPrivacyController generalPrivacyController;
    @Override
    public void buttonPressed() throws IOException {
        generalPrivacyController = new GeneralPrivacyController();
        generalPrivacyController.makeAccountPrivate();
    }
}
