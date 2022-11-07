package apps.listeners.settingListeners.logOut;

import apps.controller.setting.LogOutController;
import listeners.ButtonListener;

import java.io.IOException;

public class LogOutListener implements ButtonListener {
    private LogOutController logOutController;

    @Override
    public void buttonPressed() throws IOException {
        logOutController = new LogOutController();
        logOutController.logOut();
    }
}
