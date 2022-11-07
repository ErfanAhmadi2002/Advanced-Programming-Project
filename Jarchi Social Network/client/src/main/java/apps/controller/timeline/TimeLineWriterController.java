package apps.controller.timeline;

import apps.view.pages.timeline.TweetPage;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.timelineEvents.BlockWriterEvent;
import shared.events.timelineEvents.MuteWriterEvent;
import shared.responses.timelineResponses.BlockWriterResponse;
import shared.responses.timelineResponses.MuteWriterResponse;

public class TimeLineWriterController extends Controller {

    public TimeLineWriterController() {
    }

    public void blockWriter (TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        BlockWriterEvent blockWriterEvent = new BlockWriterEvent(LogicalAgent.authToken , page.getCurrentTweet().getWriter());
        BlockWriterResponse blockWriterResponse = (BlockWriterResponse) socketEventSender.request(blockWriterEvent);
    }

    public void muteWriter (TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        MuteWriterEvent muteWriterEvent = new MuteWriterEvent(LogicalAgent.authToken , page.getCurrentTweet().getWriter());
        MuteWriterResponse muteWriterResponse = (MuteWriterResponse) socketEventSender.request(muteWriterEvent);
    }

}
