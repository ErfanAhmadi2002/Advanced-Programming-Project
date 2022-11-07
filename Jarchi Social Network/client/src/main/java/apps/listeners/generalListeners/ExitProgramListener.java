package apps.listeners.generalListeners;

import apps.controller.general.ExitProgramController;
import listeners.ButtonListener;

import java.io.IOException;

public class ExitProgramListener implements ButtonListener {
    private ExitProgramController exitProgramController;
    @Override
    public void buttonPressed() throws IOException {
        exitProgramController = new ExitProgramController();
        exitProgramController.exitProgram();
    }
}
