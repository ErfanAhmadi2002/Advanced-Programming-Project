package apps.listeners.timelineListeners.writerCommandListeners;

import apps.controller.timeline.TimeLineWriterController;
import apps.view.pages.timeline.TweetPage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class MuteWriterListener implements PageListener {
    private TimeLineWriterController timeLineWriterController;
    @Override
    public void eventOccurred(Page source) {
        timeLineWriterController = new TimeLineWriterController();
        timeLineWriterController.muteWriter((TweetPage) source);
    }
}
