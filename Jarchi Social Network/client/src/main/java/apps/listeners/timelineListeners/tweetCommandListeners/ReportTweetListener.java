package apps.listeners.timelineListeners.tweetCommandListeners;

import apps.controller.timeline.TimeLineTweetController;
import apps.view.pages.timeline.TweetPage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class ReportTweetListener implements PageListener {
    private TimeLineTweetController timeLineTweetController;
    @Override
    public void eventOccurred(Page source) {
        timeLineTweetController = new TimeLineTweetController();
        timeLineTweetController.reportTweet((TweetPage) source);
    }
}
