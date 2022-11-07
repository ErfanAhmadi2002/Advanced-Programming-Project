package apps.controller.timeline;

import apps.view.pages.timeline.TweetPage;
import apps.view.pages.timeline.WriteComment;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.timelineEvents.LoadCommentsEvent;
import shared.events.timelineEvents.WriteCommentEvent;
import shared.formEvents.NewCommentFormEvent;
import shared.responses.timelineResponses.LoadCommentsResponse;
import shared.responses.timelineResponses.WriteCommentResponse;
import view.ExtraPageChangers;



public class TimeLineCommentController extends Controller {

    public TimeLineCommentController() {
    }

    public void writeComment(WriteComment page, NewCommentFormEvent formEvent) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        WriteCommentEvent writeCommentEvent = new WriteCommentEvent(LogicalAgent.authToken, formEvent, page.getCurrentTweet().getId());
        WriteCommentResponse writeCommentResponse = (WriteCommentResponse) socketEventSender.request(writeCommentEvent);
    }

    public void showComments(TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadCommentsEvent loadCommentsEvent = new LoadCommentsEvent(LogicalAgent.authToken, page.getCurrentTweet().getId());
        LoadCommentsResponse loadCommentsResponse = (LoadCommentsResponse) socketEventSender.request(loadCommentsEvent);
        ExtraPageChangers.goToTimeLineListPage(loadCommentsResponse.getTweets());
    }

}
