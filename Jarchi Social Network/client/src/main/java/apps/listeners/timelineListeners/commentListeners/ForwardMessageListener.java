package apps.listeners.timelineListeners.commentListeners;

import apps.controller.timeline.TimeLineTweetController;
import apps.view.pages.timeline.ForwardMessage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class ForwardMessageListener implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        TimeLineTweetController timeLineTweetController = new TimeLineTweetController();
        timeLineTweetController.forwardTweet((ForwardMessage) source);
    }
}
