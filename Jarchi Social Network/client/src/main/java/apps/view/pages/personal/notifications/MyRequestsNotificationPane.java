package apps.view.pages.personal.notifications;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

public class MyRequestsNotificationPane extends Page {
    @FXML
    private Text message;

    public void startPage (UserCopy user , int state){
        if (state == 1) {
            message.setText("The user " + user.getUserName() + " did not accept your request.");
        }
        if (state == 2) {
            message.setText("The user " + user.getUserName() + " has accepted your request.");
        }
    }
}
