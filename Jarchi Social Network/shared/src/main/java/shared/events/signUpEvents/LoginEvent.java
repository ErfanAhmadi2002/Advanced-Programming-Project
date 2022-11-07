package shared.events.signUpEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.LoginFormEvent;
import shared.responses.Response;

public class LoginEvent extends Event {
    private final LoginFormEvent loginFormEvent;

    public LoginEvent(int authToken , LoginFormEvent loginFormEvent) {
        super(authToken);
        this.loginFormEvent = loginFormEvent;
    }

    public LoginFormEvent getLoginFormEvent() {
        return loginFormEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.login(loginFormEvent);
    }

}
