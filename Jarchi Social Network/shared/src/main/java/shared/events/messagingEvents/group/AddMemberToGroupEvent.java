package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class AddMemberToGroupEvent extends Event {
    private final boolean isNewGroup;
    private final String groupName;
    private final String userName;

    public AddMemberToGroupEvent(int authToken, boolean isNewGroup, String groupName, String userName) {
        super(authToken);
        this.isNewGroup = isNewGroup;
        this.groupName = groupName;
        this.userName = userName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.addMemberToGroup(this);
    }

    public boolean isNewGroup() {
        return isNewGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserName() {
        return userName;
    }
}
