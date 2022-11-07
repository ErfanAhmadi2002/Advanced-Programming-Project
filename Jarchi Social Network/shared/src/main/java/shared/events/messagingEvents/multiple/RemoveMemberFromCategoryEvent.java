package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class RemoveMemberFromCategoryEvent extends Event {
    private final String categoryName;
    private final UserCopy member;

    public RemoveMemberFromCategoryEvent(int authToken, String categoryName, UserCopy member) {
        super(authToken);
        this.categoryName = categoryName;
        this.member = member;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.removeMemberFromCategory(this);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public UserCopy getMember() {
        return member;
    }
}
