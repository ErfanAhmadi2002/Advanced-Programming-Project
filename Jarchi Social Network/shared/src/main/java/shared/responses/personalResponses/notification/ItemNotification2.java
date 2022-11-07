package shared.responses.personalResponses.notification;

import shared.models.UserCopy;

public class ItemNotification2 {
    private final UserCopy key;
    private final Boolean value;

    public ItemNotification2(UserCopy key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public UserCopy getKey() {
        return key;
    }

    public Boolean getValue() {
        return value;
    }
}
