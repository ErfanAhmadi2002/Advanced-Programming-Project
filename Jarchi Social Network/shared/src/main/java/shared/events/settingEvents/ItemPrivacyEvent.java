package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class ItemPrivacyEvent extends Event {
    private final boolean privacyState;
    private final int itemType;

    public ItemPrivacyEvent(int authToken, boolean privacyState, int itemType) {
        super(authToken);
        this.privacyState = privacyState;
        this.itemType = itemType;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changeItemPrivacy(this);
    }

    public boolean isPrivacyState() {
        return privacyState;
    }

    public int getItemType() {
        return itemType;
    }
}
