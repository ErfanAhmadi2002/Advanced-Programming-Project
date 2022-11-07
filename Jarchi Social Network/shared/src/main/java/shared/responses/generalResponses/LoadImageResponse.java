package shared.responses.generalResponses;

import shared.models.MyImage;
import shared.responses.Response;

public class LoadImageResponse extends Response {
    private final MyImage image;

    public LoadImageResponse(MyImage image) {
        this.image = image;
    }

    public MyImage getImage() {
        return image;
    }
}
