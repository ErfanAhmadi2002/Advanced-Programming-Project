package shared.responses.generalResponses;

import shared.responses.Response;

public class MuteUserResponse extends Response {
    private final boolean state;

    public MuteUserResponse(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
}
