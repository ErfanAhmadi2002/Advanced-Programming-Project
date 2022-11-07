package shared.responses.generalResponses;

import shared.models.MyImage;
import shared.responses.Response;

public class UpdateProfileDetailsResponse extends Response {
    private final String userName;
    private final String name;
    private final String birthDate;
    private final String email;
    private final String phoneNumber;
    private final String lastSeen;
    private final boolean followingState;
    private final boolean onlineState;
    private final MyImage image;

    public UpdateProfileDetailsResponse(String userName, String name, String birthDate, String email, String phoneNumber, String lastSeen, boolean followingState, MyImage image, boolean onlineState) {
        this.userName = userName;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastSeen = lastSeen;
        this.followingState = followingState;
        this.onlineState = onlineState;
        this.image = image;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public boolean isFollowingState() {
        return followingState;
    }

    public MyImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isOnlineState() {
        return onlineState;
    }
}
