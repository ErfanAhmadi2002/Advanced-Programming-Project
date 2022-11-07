package shared.responses.signUpResponses;
import shared.models.UserCopy;
import shared.responses.Response;

public class RegistrationResponse extends Response {
    private final boolean userNameValidity;
    private final boolean emailAddressValidity;
    private final boolean phoneNumberValidity;
    private final int authToken;
    private final UserCopy user;

    public RegistrationResponse(boolean userNameValidity, boolean emailAddressValidity, boolean phoneNumberValidity, int authToken, UserCopy user) {
        this.userNameValidity = userNameValidity;
        this.emailAddressValidity = emailAddressValidity;
        this.phoneNumberValidity = phoneNumberValidity;
        this.authToken = authToken;
        this.user = user;
    }

    public boolean isUserNameValidity() {
        return userNameValidity;
    }

    public boolean isEmailAddressValidity() {
        return emailAddressValidity;
    }

    public boolean isPhoneNumberValidity() {
        return phoneNumberValidity;
    }

    public int getAuthToken() {
        return authToken;
    }

    public UserCopy getUser() {
        return user;
    }
}
