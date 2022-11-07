package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class GeneralPrivacyEvent extends Event {
    private final boolean privacyState;

    public GeneralPrivacyEvent(int authToken, boolean privacyState) {
        super(authToken);
        this.privacyState = privacyState;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changeGeneralPrivacy(this);
    }

    public boolean isPrivacyState() {
        return privacyState;
    }
}
