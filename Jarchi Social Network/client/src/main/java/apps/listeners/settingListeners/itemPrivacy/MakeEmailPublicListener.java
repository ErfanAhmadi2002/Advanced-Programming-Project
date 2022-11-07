package apps.listeners.settingListeners.itemPrivacy;

import apps.controller.setting.ItemPrivacyController;
import listeners.ButtonListener;

import java.io.IOException;

public class MakeEmailPublicListener implements ButtonListener {
    private ItemPrivacyController itemPrivacyController;
    @Override
    public void buttonPressed() throws IOException {
        itemPrivacyController = new ItemPrivacyController();
        itemPrivacyController.makeEmailPublic();
    }
}
