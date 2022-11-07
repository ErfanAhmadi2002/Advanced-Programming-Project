package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class UploadProfileImageEvent extends Event {
    private final byte[] image;

    public UploadProfileImageEvent(int authToken, byte[] image) {
        super(authToken);
        this.image = image;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.uploadProfileImage(this);
    }

    public byte[] getImage() {
        return image;
    }
}
