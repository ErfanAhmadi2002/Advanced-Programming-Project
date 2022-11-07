package apps.listeners.personalListeners.myTweets;

import apps.controller.personal.MyTweetsController;
import apps.view.pages.personal.CreateNewTweet;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class WriteNewTweetListener implements PageListener {
    private MyTweetsController myTweetsController;
    @Override
    public void eventOccurred(Page source) {
        myTweetsController = new MyTweetsController();
        myTweetsController.writeNewTweet((CreateNewTweet) source);
    }
}
