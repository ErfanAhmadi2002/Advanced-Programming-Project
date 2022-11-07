package apps.listeners.personalListeners.myTweets;

import apps.controller.personal.MyTweetsController;
import apps.view.pages.personal.myTweets.TweetPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class LoadImageTweetPaneListener implements PageListener {
    private MyTweetsController myTweetsController;
    @Override
    public void eventOccurred(Page source) {
        myTweetsController = new MyTweetsController();
        myTweetsController.loadTweetImage((TweetPane) source);
    }
}
