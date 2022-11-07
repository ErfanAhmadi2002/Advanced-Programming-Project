package apps.listeners.timelineListeners.iteratorListeners;

import apps.controller.timeline.TimeLineIteratingController;
import apps.view.pages.timeline.TweetPane;
import listeners.PageListener;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToTweetPageListener implements PageListener {
    private TimeLineIteratingController timeLineIteratingController;

    @Override
    public void eventOccurred(Page source) {
        timeLineIteratingController = new TimeLineIteratingController();
        ExtraPageChangers.goToTweetPage(((TweetPane)source).getTweetCopy());
    }
}
