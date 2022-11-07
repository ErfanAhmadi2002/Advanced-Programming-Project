package apps.listeners.settingListeners.pageChanger;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToDeleteAccountListener implements ButtonListener {
    @Override
    public void buttonPressed() throws IOException {
        LogicalAgent.viewManager.LoadScene("deleteAccount");
    }
}
