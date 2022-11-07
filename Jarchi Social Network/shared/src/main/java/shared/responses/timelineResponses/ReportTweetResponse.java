package shared.responses.timelineResponses;

import shared.responses.Response;

public class ReportTweetResponse extends Response {
    private final boolean isDeleted;

    public ReportTweetResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
