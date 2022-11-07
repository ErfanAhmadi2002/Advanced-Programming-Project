package shared.responses.messagingResponses.general;

import shared.models.UserCopy;
import shared.responses.Response;

public class LoadMyUserResponse extends Response {
    private final UserCopy userCopy;

    public LoadMyUserResponse(UserCopy userCopy) {
        this.userCopy = userCopy;
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }
}
