package shared.responses.messagingResponses.multiple;

import shared.responses.Response;

public class CreateNewCategoryNameResponse extends Response {
    private final boolean isSuccessful;

    public CreateNewCategoryNameResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
