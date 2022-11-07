package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.NewPasswordFormEvent;
import shared.responses.Response;

public class ChangePasswordEvent extends Event {
    private final NewPasswordFormEvent newPasswordFormEvent;

    public ChangePasswordEvent(int authToken, NewPasswordFormEvent newPasswordFormEvent) {
        super(authToken);
        this.newPasswordFormEvent = newPasswordFormEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changePassword(this);
    }

    public NewPasswordFormEvent getNewPasswordFormEvent() {
        return newPasswordFormEvent;
    }
}
