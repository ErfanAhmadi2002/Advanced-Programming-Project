package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class CreateNewCategoryNameEvent extends Event {
    private final String categoryName;

    public CreateNewCategoryNameEvent(int authToken, String categoryName) {
        super(authToken);
        this.categoryName = categoryName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.createNewCategoryName(this);
    }

    public String getCategoryName() {
        return categoryName;
    }
}
