package shared.events.signUpEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.RegistrationFormEvent;
import shared.responses.Response;

public class RegistrationEvent extends Event {
    private final RegistrationFormEvent registrationFormEvent;

    public RegistrationEvent(int authToken , RegistrationFormEvent registrationFormEvent) {
        super(authToken);
        this.registrationFormEvent = registrationFormEvent;
    }

    public RegistrationFormEvent getRegistrationFormEvent() {
        return registrationFormEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.registration(registrationFormEvent);
    }
}
