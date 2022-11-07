package shared.responses.timelineResponses;

import shared.responses.Response;

public class ForwardTweetResponse extends Response {
    private final boolean result;

    public ForwardTweetResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return result;
    }
}
