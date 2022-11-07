package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class RemoveCategoryEvent extends Event {
    private final String categoryName;

    public RemoveCategoryEvent(int authToken, String categoryName) {
        super(authToken);
        this.categoryName = categoryName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.removeCategory(this);
    }

    public String getCategoryName() {
        return categoryName;
    }
}
