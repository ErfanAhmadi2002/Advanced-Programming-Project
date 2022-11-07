package shared.responses.explorerResponses;

import shared.models.TweetCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class GetMainTweetsResponse extends Response {
    private final ArrayList<TweetCopy> tweets;

    public GetMainTweetsResponse(ArrayList<TweetCopy> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<TweetCopy> getTweets() {
        return tweets;
    }
}
