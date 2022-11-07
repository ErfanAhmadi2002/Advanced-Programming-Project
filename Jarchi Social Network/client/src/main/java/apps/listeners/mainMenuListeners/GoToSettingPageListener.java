package apps.listeners.mainMenuListeners;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToSettingPageListener implements ButtonListener {
    @Override
    public void buttonPressed() throws IOException {
        LogicalAgent.viewManager.LoadScene("setting");
    }
}
