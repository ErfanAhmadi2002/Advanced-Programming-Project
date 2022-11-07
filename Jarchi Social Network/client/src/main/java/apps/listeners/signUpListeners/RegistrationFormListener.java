package apps.listeners.signUpListeners;

import apps.controller.signup.RegistrationController;
import apps.view.pages.signup.Register;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.RegistrationFormEvent;
import view.Page;

import java.io.IOException;

public class RegistrationFormListener implements FormListener {
    private RegistrationController registrationController;

    @Override
    public void formRequest(Form form, Page source) {
        registrationController = new RegistrationController();
        registrationController.register((RegistrationFormEvent) form, (Register) source);
    }
}
