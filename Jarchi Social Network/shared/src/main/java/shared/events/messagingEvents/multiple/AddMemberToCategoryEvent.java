package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class AddMemberToCategoryEvent extends Event {
    private final String categoryName;
    private final String memberName;
    private final boolean isOldCategory;

    public AddMemberToCategoryEvent(int authToken, String categoryName, String memberName, boolean isOldCategory) {
        super(authToken);
        this.categoryName = categoryName;
        this.memberName = memberName;
        this.isOldCategory = isOldCategory;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.addMemberToCategory(this);
    }

    public boolean isOldCategory() {
        return isOldCategory;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
