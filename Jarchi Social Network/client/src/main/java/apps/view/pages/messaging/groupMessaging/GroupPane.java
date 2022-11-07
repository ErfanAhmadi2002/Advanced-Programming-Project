package apps.view.pages.messaging.groupMessaging;

import apps.listeners.messagingListeners.groupMessaging.LeaveGroupListener;
import apps.listeners.messagingListeners.pageChanger.GoToAddGroupMemberPage;
import apps.listeners.messagingListeners.pageChanger.GoToGroupHistoryPage;
import controller.LogicalAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.Group;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupPane extends Page implements Initializable {
    private Group group;
    private PageListener addMemberListener;
    private PageListener sendMessageListener;
    private PageListener leaveGroupListener;

    @FXML
    private Text groupName;

    @FXML
    private Button addMember;

    @FXML
    private Button sendMessage;

    @FXML
    private Button leaveGroup;

    @FXML
    void addMember(ActionEvent event) {
        addMemberListener.eventOccurred(this);
    }

    @FXML
    void sendMessage(ActionEvent event)  {
        LogicalAgent.pauseFirstThread();
        sendMessageListener.eventOccurred(this);
    }

    @FXML
    void leaveGroup(ActionEvent event) {
        leaveGroupListener.eventOccurred(this);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void startPage(Group group){
        setGroup(group);
        groupName.setText(group.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMemberListener = new GoToAddGroupMemberPage();
        sendMessageListener = new GoToGroupHistoryPage();
        leaveGroupListener = new LeaveGroupListener();
    }
}
