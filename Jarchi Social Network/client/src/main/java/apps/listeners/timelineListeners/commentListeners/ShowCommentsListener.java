package apps.listeners.timelineListeners.commentListeners;

import apps.controller.timeline.TimeLineCommentController;
import apps.view.pages.timeline.TweetPage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class ShowCommentsListener implements PageListener {
    private TimeLineCommentController timeLineCommentController;

    @Override
    public void eventOccurred(Page source) {
        timeLineCommentController = new TimeLineCommentController();
        timeLineCommentController.showComments((TweetPage) source);
    }
}
