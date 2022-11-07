package shared.responses.explorerResponses;

import shared.models.UserCopy;
import shared.responses.Response;

public class SearchAccountResponse extends Response {
    private final UserCopy userCopy;

    public SearchAccountResponse(UserCopy userCopy) {
        this.userCopy = userCopy;
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }
}
