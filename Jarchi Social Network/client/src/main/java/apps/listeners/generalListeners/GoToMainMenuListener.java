package apps.listeners.generalListeners;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToMainMenuListener implements ButtonListener {
    @Override
    public void buttonPressed() throws IOException {
        LogicalAgent.stopAllThreads();
        LogicalAgent.viewManager.LoadScene("mainMenu");
    }
}
