package listeners;

import shared.formEvents.Form;
import view.Page;

import java.io.IOException;

public interface FormListener {
    void formRequest(Form form , Page source) ;
}
