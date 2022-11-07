package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class AccountActivationEvent extends Event {
    private final boolean activationState;

    public AccountActivationEvent(int authToken, boolean activationState) {
        super(authToken);
        this.activationState = activationState;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.accountActivation(this);
    }

    public boolean isActivationState() {
        return activationState;
    }
}
