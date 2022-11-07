package apps.listeners.signUpListeners;

import apps.controller.signup.LoginController;
import apps.view.pages.signup.Login;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.LoginFormEvent;
import view.Page;

import java.io.IOException;

public class LoginFormListener implements FormListener {
    private LoginController loginController;
    @Override
    public void formRequest(Form form, Page source) {
        loginController = new LoginController();
        loginController.login((LoginFormEvent) form , (Login) source);
    }
}
