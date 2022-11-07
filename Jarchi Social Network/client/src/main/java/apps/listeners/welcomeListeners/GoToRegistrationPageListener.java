package apps.listeners.welcomeListeners;

import controller.LogicalAgent;
import listeners.ButtonListener;

import java.io.IOException;


public class GoToRegistrationPageListener implements ButtonListener {

    @Override
    public void buttonPressed() throws IOException {
        LogicalAgent.viewManager.LoadScene("register");
    }
}
