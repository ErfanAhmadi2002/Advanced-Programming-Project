package shared.responses.messagingResponses.pv;

import shared.models.MyImage;
import shared.models.UserCopy;
import shared.responses.Response;

public class LoadSaveMessagePaneResponse extends Response {
    private final UserCopy userCopy;
    private final MyImage image;

    public LoadSaveMessagePaneResponse(UserCopy userCopy, MyImage image) {
        this.userCopy = userCopy;
        this.image = image;
    }

    public MyImage getImage() {
        return image;
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }
}
