package apps.listeners.timelineListeners.commentListeners;

import apps.controller.timeline.TimeLineCommentController;
import apps.view.pages.timeline.WriteComment;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.NewCommentFormEvent;
import view.Page;

import java.io.IOException;

public class WriteNewCommentListener implements FormListener {
    private TimeLineCommentController timeLineCommentController;
    @Override
    public void formRequest(Form form, Page source) {
        timeLineCommentController = new TimeLineCommentController();
        timeLineCommentController.writeComment((WriteComment)source , (NewCommentFormEvent)form);
    }
}
