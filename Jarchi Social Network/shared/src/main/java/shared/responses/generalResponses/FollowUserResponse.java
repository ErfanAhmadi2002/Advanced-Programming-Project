package shared.responses.generalResponses;

import shared.responses.Response;

public class FollowUserResponse extends Response {
    private final boolean state;

    public FollowUserResponse(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
}
