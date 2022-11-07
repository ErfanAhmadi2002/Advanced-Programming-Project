package shared.events.generalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadImageEvent extends Event {
    private final int imageId;

    public LoadImageEvent(int authToken, int imageId) {
        super(authToken);
        this.imageId = imageId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadImage(this);
    }

    public int getImageId() {
        return imageId;
    }

}
