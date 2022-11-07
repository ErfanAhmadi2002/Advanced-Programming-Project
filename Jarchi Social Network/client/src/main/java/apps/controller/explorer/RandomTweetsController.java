package apps.controller.explorer;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.explorerEvents.GetMainTweetsEvent;
import shared.responses.explorerResponses.GetMainTweetsResponse;
import view.ExtraPageChangers;

public class RandomTweetsController extends Controller {

    public RandomTweetsController() {
    }

    public void getMainTweets() {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        GetMainTweetsEvent getMainTweetsEvent = new GetMainTweetsEvent(LogicalAgent.authToken);
        GetMainTweetsResponse getMainTweetsResponse = (GetMainTweetsResponse) socketEventSender.request(getMainTweetsEvent);
        ExtraPageChangers.goToTimeLineListPage(getMainTweetsResponse.getTweets());
    }
}
