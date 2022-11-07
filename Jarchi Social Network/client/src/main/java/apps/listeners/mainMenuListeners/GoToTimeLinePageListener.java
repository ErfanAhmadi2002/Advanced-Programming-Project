package apps.listeners.mainMenuListeners;

import apps.controller.timeline.TimeLineIteratingController;
import listeners.ButtonListener;

import java.io.IOException;

public class GoToTimeLinePageListener implements ButtonListener {
    private TimeLineIteratingController timeLineIteratingController;

    @Override
    public void buttonPressed() throws IOException {
        timeLineIteratingController = new TimeLineIteratingController();
        timeLineIteratingController.getFirstTweetSeries();
    }
}
