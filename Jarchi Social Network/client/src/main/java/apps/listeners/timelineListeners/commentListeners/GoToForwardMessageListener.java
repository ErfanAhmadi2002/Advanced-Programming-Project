package apps.listeners.timelineListeners.commentListeners;

import apps.view.pages.timeline.TweetPage;
import listeners.PageListener;
import shared.models.TweetCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToForwardMessageListener implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        TweetCopy currentTweet = ((TweetPage)source).getCurrentTweet();
        ExtraPageChangers.goToForwardMessagePage(currentTweet);
    }
}
