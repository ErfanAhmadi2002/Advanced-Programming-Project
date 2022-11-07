package shared.responses.timelineResponses;

import shared.models.TweetCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadCommentsResponse extends Response {
    private final ArrayList<TweetCopy> tweets;

    public LoadCommentsResponse(ArrayList<TweetCopy> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<TweetCopy> getTweets() {
        return tweets;
    }
}
