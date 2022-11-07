package shared.responses.personalResponses.notification;

import shared.models.UserCopy;

public class ItemNotification1 {
    private final UserCopy key;
    private final Integer value;

    public ItemNotification1(UserCopy key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public UserCopy getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
