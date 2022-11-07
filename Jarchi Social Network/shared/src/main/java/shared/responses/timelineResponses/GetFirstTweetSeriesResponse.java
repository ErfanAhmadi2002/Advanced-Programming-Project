package shared.responses.timelineResponses;

import shared.models.TweetCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class GetFirstTweetSeriesResponse extends Response {
    private final ArrayList<TweetCopy> ids;

    public GetFirstTweetSeriesResponse(ArrayList<TweetCopy> ids) {
        this.ids = ids;
    }

    public ArrayList<TweetCopy> getIds() {
        return ids;
    }
}
