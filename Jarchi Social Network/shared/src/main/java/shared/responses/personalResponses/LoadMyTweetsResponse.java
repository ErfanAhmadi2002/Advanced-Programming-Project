package shared.responses.personalResponses;

import shared.models.Tweet;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadMyTweetsResponse extends Response {
    private final ArrayList<Tweet> myTweets;

    public LoadMyTweetsResponse(ArrayList<Tweet> myTweets) {
        this.myTweets = myTweets;
    }

    public ArrayList<Tweet> getMyTweets() {
        return myTweets;
    }
}
