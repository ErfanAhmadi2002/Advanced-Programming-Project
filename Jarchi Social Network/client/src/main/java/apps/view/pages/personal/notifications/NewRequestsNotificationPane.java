package apps.view.pages.personal.notifications;

import apps.listeners.personalListeners.notifications.AcceptRequestListener;
import apps.listeners.personalListeners.notifications.RejectRequestListener;
import apps.listeners.personalListeners.notifications.SafeRejectListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.UserCopy;
import view.Page;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRequestsNotificationPane extends Page implements Initializable {
    private UserCopy requested;
    private PageListener acceptButtonListener;
    private PageListener rejectButtonListener;
    private PageListener safeRejectButtonListener;

    @FXML
    private Text username;

    @FXML
    private Button accept;

    @FXML
    private Button reject;

    @FXML
    private Button safeReject;

    public void setRequested(UserCopy requested) {
        this.requested = requested;
    }

    public UserCopy getRequested() {
        return requested;
    }

    @FXML
    void accept(ActionEvent event) {
        acceptButtonListener.eventOccurred(this);
    }

    @FXML
    void reject(ActionEvent event) {
        rejectButtonListener.eventOccurred(this);
    }

    @FXML
    void safeReject(ActionEvent event) {
        safeRejectButtonListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acceptButtonListener = new AcceptRequestListener();
        rejectButtonListener = new RejectRequestListener();
        safeRejectButtonListener = new SafeRejectListener();
    }

    public void startPage (UserCopy user){
        setRequested(user);
        username.setText(requested.getUserName());
    }
}
