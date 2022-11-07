package apps.listeners.mainMenuListeners;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToExplorerPageListener implements ButtonListener {
    @Override
    public void buttonPressed() throws IOException {
        if (LogicalAgent.socketEventSender != null){
            LogicalAgent.viewManager.LoadScene("explorer");
        }
    }
}
