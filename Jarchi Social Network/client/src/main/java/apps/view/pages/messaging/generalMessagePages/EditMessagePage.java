package apps.view.pages.messaging.generalMessagePages;

import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.messagingListeners.monoMessaging.EditMessageListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.Message;
import shared.models.MessageCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditMessagePage extends Page implements Initializable {
    private MessageCopy message;
    private ButtonListener backButtonListener;
    private PageListener submitButtonListener;

    @FXML
    private TextArea textArea;

    @FXML
    private Button submit;

    @FXML
    void submit(ActionEvent event) throws IOException {
        submitButtonListener.eventOccurred(this);
        backButtonListener.buttonPressed();
    }

    public MessageCopy getMessage() {
        return message;
    }

    public void setMessage(MessageCopy message) {
        this.message = message;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonListener = new GoBackListener();
        submitButtonListener = new EditMessageListener();
    }
}
