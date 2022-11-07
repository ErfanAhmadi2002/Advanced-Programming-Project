package apps.controller.timeline;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.timelineEvents.GetFirstTweetSeriesEvent;
import shared.responses.timelineResponses.GetFirstTweetSeriesResponse;
import view.ExtraPageChangers;

import java.io.IOException;

public class TimeLineIteratingController extends Controller {

    public TimeLineIteratingController() {
    }

    public void getFirstTweetSeries() {
        if (LogicalAgent.socketEventSender != null){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            GetFirstTweetSeriesEvent getFirstTweetSeriesEvent = new GetFirstTweetSeriesEvent(LogicalAgent.authToken);
            GetFirstTweetSeriesResponse getFirstTweetSeriesResponse = (GetFirstTweetSeriesResponse) socketEventSender.request(getFirstTweetSeriesEvent);
            ExtraPageChangers.goToTimeLineListPage(getFirstTweetSeriesResponse.getIds());
        }
    }


}
