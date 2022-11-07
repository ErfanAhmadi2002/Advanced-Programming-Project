package shared.responses.messagingResponses.multiple;

import shared.responses.Response;

public class AddMemberToCategoryResponse extends Response {
    private final boolean addResult;

    public AddMemberToCategoryResponse(boolean addResult) {
        this.addResult = addResult;
    }

    public boolean isAddResult() {
        return addResult;
    }
}
