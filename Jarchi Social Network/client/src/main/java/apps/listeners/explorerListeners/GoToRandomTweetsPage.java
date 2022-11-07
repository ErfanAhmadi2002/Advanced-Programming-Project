package apps.listeners.explorerListeners;

import apps.controller.explorer.RandomTweetsController;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToRandomTweetsPage implements ButtonListener {
    private RandomTweetsController randomTweetsController;

    @Override
    public void buttonPressed() throws IOException {
        randomTweetsController = new RandomTweetsController();
        randomTweetsController.getMainTweets();
    }
}
