package shared.responses.signUpResponses;

import shared.models.UserCopy;
import shared.responses.Response;

public class LoginResponse extends Response {
    private final int authToken;
    private final UserCopy user;
    private final boolean isValidInput;

    public LoginResponse(int authToken, UserCopy user, boolean isValidInput) {
        this.authToken = authToken;
        this.user = user;
        this.isValidInput = isValidInput;
    }

    public int getAuthToken() {
        return authToken;
    }

    public boolean isValidInput() {
        return isValidInput;
    }

    public UserCopy getUser() {
        return user;
    }
}
