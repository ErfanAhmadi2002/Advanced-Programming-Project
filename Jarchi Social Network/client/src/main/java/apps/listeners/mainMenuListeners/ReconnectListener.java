package apps.listeners.mainMenuListeners;

import apps.controller.general.ReconnectController;
import listeners.ButtonListener;

import java.io.IOException;

public class ReconnectListener implements ButtonListener {

    @Override
    public void buttonPressed() throws IOException {
        ReconnectController reconnectController = new ReconnectController();
        reconnectController.reconnect();
    }
}
