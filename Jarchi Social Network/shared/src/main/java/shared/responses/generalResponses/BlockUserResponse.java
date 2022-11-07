package shared.responses.generalResponses;

import shared.responses.Response;

public class BlockUserResponse extends Response {
    private final boolean state;

    public BlockUserResponse(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
}
