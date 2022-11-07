package apps.listeners.generalListeners;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;

public class GoBackListener implements ButtonListener {

    @Override
    public void buttonPressed() throws IOException {
        LogicalAgent.viewManager.back();
    }

}
