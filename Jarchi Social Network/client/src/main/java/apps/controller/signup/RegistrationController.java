package apps.controller.signup;

import apps.view.pages.signup.Register;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.signUpEvents.RegistrationEvent;
import shared.formEvents.RegistrationFormEvent;
import shared.responses.signUpResponses.RegistrationResponse;

import java.io.IOException;

public class RegistrationController extends Controller {

    public RegistrationController() {
    }

    public void register(RegistrationFormEvent formEvent, Register page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        RegistrationEvent registrationEvent = new RegistrationEvent(0 , formEvent);
        RegistrationResponse registrationResponse = (RegistrationResponse) socketEventSender.request(registrationEvent);
        boolean isValidUser = true;
        if (!registrationResponse.isUserNameValidity()) {
            page.usernameUsedBeforeError();
            isValidUser = false;
        }
        if (!registrationResponse.isPhoneNumberValidity()) {
            page.phoneNumberUsedBeforeError();
            isValidUser = false;
        }
        if (!registrationResponse.isEmailAddressValidity()) {
            page.emailUsedBeforeError();
            isValidUser = false;
        }
        if (isValidUser) {
            LogicalAgent.authToken = registrationResponse.getAuthToken();
            LogicalAgent.viewManager.setUser(registrationResponse.getUser());
            LogicalAgent.viewManager.LoadScene("mainMenu");

        }
    }

}
