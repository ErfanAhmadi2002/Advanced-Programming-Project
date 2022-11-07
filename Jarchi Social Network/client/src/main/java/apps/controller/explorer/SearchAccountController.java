package apps.controller.explorer;

import apps.view.pages.explorer.SearchAccount;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.explorerEvents.SearchAccountEvent;
import shared.responses.explorerResponses.SearchAccountResponse;
import view.ExtraPageChangers;

import java.io.IOException;

public class SearchAccountController extends Controller {

    public SearchAccountController() {

    }

    public void searchForAccount(SearchAccount page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        SearchAccountEvent searchAccountEvent = new SearchAccountEvent(LogicalAgent.authToken, page.getUsername().getText());
        SearchAccountResponse searchAccountResponse = (SearchAccountResponse) socketEventSender.request(searchAccountEvent);
        if (searchAccountResponse != null) {
            ExtraPageChangers.goToProfilePage(searchAccountResponse.getUserCopy());
        } else {
            page.errorOccurred();
        }
    }
}
