package apps.listeners.timelineListeners.commentListeners;

import apps.view.pages.timeline.TweetPage;
import listeners.PageListener;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToWriteCommentListener implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        ExtraPageChangers.goToWriteCommentPage(((TweetPage)source).getCurrentTweet());
    }
}
